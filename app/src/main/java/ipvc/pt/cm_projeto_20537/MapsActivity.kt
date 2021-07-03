package ipvc.pt.cm_projeto_20537

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ipvc.pt.cm_projeto_20537.api.EndPoints
import ipvc.pt.cm_projeto_20537.api.Problema
import ipvc.pt.cm_projeto_20537.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var shared_preferences: SharedPreferences
    private lateinit var  problemas: List<Problema>
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        shared_preferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getProblemas()
        var coordenadas: LatLng
        val user_id = shared_preferences.getInt("id", 0)

        call.enqueue(object : Callback<List<Problema>> {
            override fun onResponse(call: Call<List<Problema>>, response: Response<List<Problema>>){
                if (response.isSuccessful){
                    problemas = response.body()!!
                    for (problema in problemas){
                        coordenadas = LatLng(problema.latitude.toDouble(), problema.longitude.toDouble())
                        if(problema.user_id == user_id){
                            mMap.addMarker(MarkerOptions().position(coordenadas).title(problema.id.toString()).snippet(problema.titulo + "-" + problema.descricao))
                                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        }else{
                            mMap.addMarker(MarkerOptions().position(coordenadas).title(problema.id.toString()).snippet(problema.titulo + "-" + problema.descricao))
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Problema>>, t: Throwable){
                Toast.makeText(this@MapsActivity,"${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setUpMap()
    }

    private fun setUpMap() {
        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

            return
        } else{
            mMap.isMyLocationEnabled = true

            fusedLocationClient.lastLocation.addOnSuccessListener(this) {location ->

                if(location != null){
                    lastLocation = location
                    //Toast.makeText(this@MapsActivity, lastLocation.toString(), Toast.LENGTH_SHORT).show()
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                }
            }
        }
    }
}