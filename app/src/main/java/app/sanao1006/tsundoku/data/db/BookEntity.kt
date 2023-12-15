package app.sanao1006.tsundoku.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val totalPage : Int,
    @ColumnInfo(name = "now_page")
    var nowPage : Int = 0,
    @ColumnInfo(name = "created_at")
    val createdAt : String,
    @ColumnInfo(name = "updated_at")
    val updatedAt : String,
)
