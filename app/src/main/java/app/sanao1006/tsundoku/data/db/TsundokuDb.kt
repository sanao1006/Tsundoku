package app.sanao1006.tsundoku.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        BookEntity::class,
        CommentEntity::class,
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
        )
    ],
    exportSchema = true
)
abstract class TsundokuDb : RoomDatabase() {
    abstract fun bookDao(): BookDao

    abstract fun commentDao(): CommentDao

    companion object {
        val MIGRATE1TO2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS comments(" +
                        "comment_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        "id INTEGER NOT NULL," +
                        "comment TEXT NOT NULL," +
                        "created_at TEXT NOT NULL," +
                        "updated_at TEXT NOT NULL," +
                        "FOREIGN KEY(comment_id) REFERENCES books(id) ON DELETE CASCADE" +
                        ");"
                )
            }
        }
    }
}
