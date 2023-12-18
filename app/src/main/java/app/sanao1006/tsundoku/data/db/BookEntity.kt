package app.sanao1006.tsundoku.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.sanao1006.tsundoku.data.model.Book

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String? = "",
    @ColumnInfo(name = "description")
    val description: String? = "",
    @ColumnInfo(name = "total_page")
    val totalPage: Int,
    @ColumnInfo(name = "now_page")
    var nowPage: Int = 0,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
) {
    fun toBook(): Book {
        return Book(
            id = this.id,
            description = this.description ?: "",
            title = this.title ?: "",
            totalPage = this.totalPage,
            nowPage = this.nowPage,
            createAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}
