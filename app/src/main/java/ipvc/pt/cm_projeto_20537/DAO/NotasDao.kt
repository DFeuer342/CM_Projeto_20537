package ipvc.pt.cm_projeto_20537.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ipvc.pt.cm_projeto_20537.ententies.Nota

@Dao
interface NotasDao {

        @Query("SELECT * FROM nota_table ORDER BY id DESC")
        fun getNotas(): LiveData<List<Nota>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(nota: Nota)

        @Query("UPDATE nota_table SET titulo = :titulo, descricao = :descricao WHERE id = :id")
        suspend fun update(id: Int?, titulo: String, descricao: String)

        @Query("DELETE FROM nota_table WHERE id = :id")
        suspend fun delete(id: Int?)

        /*
        @Query("DELETE FROM nota_table")
        suspend fun deleteAll()

        @Query("DELETE FROM nota_table WHERE id = id" )
        suspend fun deleteByID()
        */

}
