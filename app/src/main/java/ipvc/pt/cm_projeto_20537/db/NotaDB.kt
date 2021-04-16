package ipvc.pt.cm_projeto_20537.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ipvc.pt.cm_projeto_20537.DAO.NotasDao
import ipvc.pt.cm_projeto_20537.ententies.Nota
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Nota::class], version = 1, exportSchema = false)
public abstract class NotaDB: RoomDatabase() {

    abstract fun notasDao(): NotasDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotaDB? = null


        fun getDatabase(context: Context, scope: CoroutineScope): NotaDB {
            val tempInstance = INSTANCE
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotaDB::class.java,
                    "notas_database"
                )
                    .build()

                INSTANCE = instance
                // return instance
                return instance
            }
        }
    }
}

