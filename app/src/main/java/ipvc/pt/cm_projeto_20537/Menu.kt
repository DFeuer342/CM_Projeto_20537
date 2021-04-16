package ipvc.pt.cm_projeto_20537

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    //fun onClickMenuMapa(view: View) {}
    fun onClickMenuNotas(view: View) {
        val intent = Intent(this, ListaNotas::class.java)
        startActivity(intent)
    }
    fun onClickMenuAbout(view: View) {
        val intent = Intent(this, About::class.java)
        startActivity(intent)
    }
}