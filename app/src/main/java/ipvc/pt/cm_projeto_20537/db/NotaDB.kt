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

@Database(entities = [Nota::class], version = 3, exportSchema = false)
public abstract class NotaDB: RoomDatabase() {

    abstract fun notasDao(): NotasDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            val let = INSTANCE?.let { database ->
                scope.launch {
                    var notasDao = database.notasDao()

                    var nota = Nota(1, "Rua Oliveira", "Buracos na estrada")
                    notasDao.insert(nota)
                    nota = Nota(2, "Rua Cantinho das Flores", "Demasiado suspeita")
                    notasDao.insert(nota)
                    nota = Nota(3, "Largo S João", "Qualquer coisa com o santo")
                    notasDao.insert(nota)

                }
            }
        }
    }

        companion object {
            @Volatile
            private var INSTANCE: NotaDB? = null

            fun getDatabase(context: Context, scope: CoroutineScope): NotaDB {
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotaDB::class.java,
                        "notas_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(WordDatabaseCallback(scope))
                        .build()

                    INSTANCE = instance
                    // return instance
                    return instance
                }
            }
        }
    }


