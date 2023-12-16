package app.sanao1006.tsundoku.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {
    @Query("select * from books")
    suspend fun getBooks(): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Update(entity = BookEntity::class)
    suspend fun updateBookInfo(book: BookEntity)
}
