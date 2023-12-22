package app.sanao1006.tsundoku.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import app.sanao1006.tsundoku.data.model.Comment

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("comment_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("comment_id")
    val commentId: Int,
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("comment")
    val comment: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
) {
    fun toComment(): Comment {
        return Comment(
            commentId = this.commentId,
            comment = this.comment,
            updatedAt = this.updatedAt,
            createdAt = this.createdAt,
        )
    }
}
