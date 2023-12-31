package app.sanao1006.tsundoku.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("select * from books")
    fun getBooks(): Flow<List<BookEntity?>>

    @Query("select * from books where id = :bookId")
    fun getBook(bookId: Int): Flow<BookEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Update(entity = BookEntity::class)
    suspend fun updateBookInfo(book: BookEntity)

    @Query("DELETE FROM books WHERE id = :bookId")
    fun deleteBook(bookId: Int)
}
