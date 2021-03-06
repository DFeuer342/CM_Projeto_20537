package ipvc.pt.cm_projeto_20537

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btNotas(view: View) {
        val intent = Intent(this, ListaNotas::class.java)
        startActivity(intent)
    }

    fun btEntrar(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    fun btAbout(view: View) {
        val intent = Intent(this, About::class.java)
        startActivity(intent)
    }


}