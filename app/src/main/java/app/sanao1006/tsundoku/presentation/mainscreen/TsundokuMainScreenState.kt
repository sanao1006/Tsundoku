package app.sanao1006.tsundoku.presentation.mainscreen

import app.sanao1006.tsundoku.data.model.Book

data class TsundokuMainScreenState(
    val tsundokus: List<Book>,
    val isLoading: Boolean,
    val error: String? = null,
)
