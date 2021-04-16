package ipvc.pt.cm_projeto_20537.db

import androidx.lifecycle.LiveData
import ipvc.pt.cm_projeto_20537.AddNotas
import ipvc.pt.cm_projeto_20537.DAO.NotasDao
import ipvc.pt.cm_projeto_20537.ententies.Nota


class NotaRepositorio(private val notasDao: NotasDao) {

    val allNotas: LiveData<List<Nota>> = notasDao.getNotaByID()

    suspend fun insert(nota: Nota) {
        notasDao.insert(nota)
    }

    suspend fun delete(id: Int){
        notasDao.deleteAll()
    }
}