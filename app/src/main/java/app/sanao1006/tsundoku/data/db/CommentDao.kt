package app.sanao1006.tsundoku.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(commentEntity: CommentEntity)

    @Query("SELECT * FROM comments WHERE id = :bookId")
    fun getCommentsByBookId(bookId: Int): Flow<List<CommentEntity>>
}
