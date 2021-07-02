package ipvc.pt.cm_projeto_20537

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import ipvc.pt.cm_projeto_20537.api.EndPoints
import ipvc.pt.cm_projeto_20537.api.OutputLogin
import ipvc.pt.cm_projeto_20537.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var editTextLoginName: EditText
    private lateinit var editTextLoginPass: EditText
    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editTextLoginName = findViewById(R.id.edTextNome)
        editTextLoginPass = findViewById(R.id.edTextPass)
        checkBox = findViewById(R.id.checkManterIni)

    }

    fun btLogin(view: View) {
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val username = editTextLoginName.text.toString()
        val password = editTextLoginPass.text.toString()
        val call = request.login(username = username, password = password)

        call.enqueue(object : Callback<OutputLogin> {
            override fun onResponse(call: Call<OutputLogin>, response: Response<OutputLogin>){
                if (response.isSuccessful){
                    val c: OutputLogin = response.body()!!
                    if(TextUtils.isEmpty(editTextLoginName.text) || TextUtils.isEmpty(editTextLoginPass.text)) {
                        Toast.makeText(this@Login, "Campos vazios", Toast.LENGTH_LONG).show()
                    }else{
                        if(c.status == "false"){
                            Toast.makeText(this@Login, c.MSG, Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(this@Login, c.MSG, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<OutputLogin>, t: Throwable){
                Toast.makeText(this@Login,"${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}