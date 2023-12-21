package app.sanao1006.tsundoku.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        BookEntity::class,
        CommentEntity::class,
    ],
    version = 1
)
abstract class TsundokuDb : RoomDatabase() {
    abstract fun bookDao(): BookDao

    abstract fun commentDao(): CommentDao
}
