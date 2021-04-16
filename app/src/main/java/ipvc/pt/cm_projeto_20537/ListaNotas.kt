package ipvc.pt.cm_projeto_20537

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.pt.cm_projeto_20537.adapters.NotaAdapter
import ipvc.pt.cm_projeto_20537.viewModel.NotaViewModel

class ListaNotas : AppCompatActivity() {
    //private lateinit var NotaViewModel: NotaViewModel
    private val newWordActivityRequestCode = 1;

    val NotaViewModel: NotaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotaAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val viewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        NotaViewModel.allNotas.observe(this, Observer { notas ->
            notas?.let{ adapter.setNotas(it)}
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this@ListaNotas, Menu::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }
}