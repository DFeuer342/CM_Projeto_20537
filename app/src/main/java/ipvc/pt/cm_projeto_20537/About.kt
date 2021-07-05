package ipvc.pt.cm_projeto_20537

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about2)
    }

    fun onClickAboutReturn() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickAboutReturn(view: View) {}
}