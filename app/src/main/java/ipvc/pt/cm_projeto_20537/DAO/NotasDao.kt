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

        @Query("DELETE FROM nota_table")
        suspend fun deleteAll()

        @Query("DELETE FROM nota_table WHERE id = id" )
        suspend fun deleteByID()
}
