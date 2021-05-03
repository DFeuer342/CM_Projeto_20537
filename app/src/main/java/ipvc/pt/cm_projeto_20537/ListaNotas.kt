package ipvc.pt.cm_projeto_20537

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.pt.cm_projeto_20537.adapters.NotaAdapter
import ipvc.pt.cm_projeto_20537.ententies.Nota
import ipvc.pt.cm_projeto_20537.viewModel.NotaViewModel

class ListaNotas : AppCompatActivity() {
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
            notas?.let { adapter.setNotas(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@ListaNotas, AddNotas::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newWordActivityRequestCode) {
            if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
                var titulo = data?.getStringExtra(AddNotas.EXTRA_REPLY_TITLE).toString()
                var descricao = data?.getStringExtra(AddNotas.EXTRA_REPLY_CONTENT).toString()
                var nota = Nota(titulo = titulo, descricao = descricao)
                NotaViewModel.insert(nota)
                Toast.makeText(this, "Nota guardada.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Dados vazios. Operação cancelada",
                    Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Nota guardada.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}