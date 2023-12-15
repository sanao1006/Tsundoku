package app.sanao1006.tsundoku.data.model

data class Book(
    val id: Int,
    val title: String,
    val description: String,
    val totalPage: Int,
    var nowPage: Int = 0,
    val createAt: String,
    var updatedAt: String,
)
