package ipvc.pt.cm_projeto_20537.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ipvc.pt.cm_projeto_20537.db.NotaDB
import ipvc.pt.cm_projeto_20537.db.NotaRepositorio
import ipvc.pt.cm_projeto_20537.ententies.Nota
import kotlinx.coroutines.launch

class NotaViewModel (application: Application) : AndroidViewModel(application) {

    private val repositorio: NotaRepositorio
    val allNotas: LiveData<List<Nota>>

    init {
        val  nDao = NotaDB.getDatabase(application, viewModelScope).notasDao()
        repositorio = NotaRepositorio(nDao)
        allNotas = repositorio.allNotas
    }

    fun insert(nota: Nota) = viewModelScope.launch{
        repositorio.insert(nota)
    }

    fun update(id: Int?, titulo: String, observacao: String) = viewModelScope.launch{
        repositorio.update(id, titulo, observacao)
    }

    fun delete(id: Int?) = viewModelScope.launch{
        repositorio.delete(id)
    }
}


