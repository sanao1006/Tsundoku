package app.sanao1006.tsundoku.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

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
}
