package ipvc.pt.cm_projeto_20537.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ipvc.pt.cm_projeto_20537.DAO.NotasDao
import ipvc.pt.cm_projeto_20537.ententies.Nota
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Nota::class], version = 1, exportSchema = false)
public abstract class NotaDB: RoomDatabase() {

    abstract fun notasDao(): NotasDao

    private class NotaDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var notaDao = database.notasDao()

                    notaDao.deleteAll()

                    var nota = Nota(1, "Rua Oliveira", "Buracos na estrada")
                    notaDao.insert(nota)
                    Nota(2, "Rua Cantinho das Flores", "Demasiado suspeita")
                    notaDao.insert(nota)
                    Nota(2, "Largo S João", "Qualquer coisa com o santo")
                    notaDao.insert(nota)

                }
            }
        }


        companion object {
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
}

