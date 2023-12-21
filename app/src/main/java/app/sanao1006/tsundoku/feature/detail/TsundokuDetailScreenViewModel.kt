package app.sanao1006.tsundoku.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sanao1006.tsundoku.data.di.TsundokuRepository
import app.sanao1006.tsundoku.data.model.Book
import app.sanao1006.tsundoku.data.model.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TsundokuDetailScreenViewModel @Inject constructor(
    private val tsundokuRepository: TsundokuRepository
) : ViewModel() {
    private val _book = MutableStateFlow(
        Book(
            id = 0,
            title = "",
            description = "",
            totalPage = 0,
            createAt = "",
            updatedAt = ""
        )
    )
    val book = _book.asStateFlow()

    private val _comments = MutableStateFlow<List<Comment>>(listOf())
    val comments = _comments.asStateFlow()

    fun getBook(bookId: Int) = viewModelScope.launch {
        tsundokuRepository.getBook(bookId = bookId).collect {
            _book.value = it
        }
    }

    fun getCommentsByBookId(bookId: Int) = viewModelScope.launch {
        tsundokuRepository.getCommentsByBookId(bookId = bookId).collect {
            _comments.value = it
        }
    }
}
