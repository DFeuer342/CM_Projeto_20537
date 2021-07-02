package ipvc.pt.cm_projeto_20537

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import android.view.View

class Menu : AppCompatActivity() {

    private lateinit var shared_preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        shared_preferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
    }

    fun btSair(view: View){
        val shared_preferences_edit : SharedPreferences.Editor = shared_preferences.edit()
        shared_preferences_edit.clear()
        shared_preferences_edit.apply()

        val intent = Intent(this@Menu, Login::class.java)
        startActivity(intent)
        finish()
    }

}