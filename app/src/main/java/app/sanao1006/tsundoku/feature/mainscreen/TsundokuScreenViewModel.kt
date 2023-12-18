package app.sanao1006.tsundoku.feature.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sanao1006.tsundoku.data.di.TsundokuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TsundokuScreenViewModel @Inject constructor(
    private val tsundokuRepository: TsundokuRepository
) : ViewModel() {
    private val _tsundokuState = MutableStateFlow(
        TsundokuMainScreenState(
            tsundokus = listOf(),
            isLoading = true
        )
    )
    val tsundokuState = _tsundokuState.asStateFlow()

    init {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch {
            tsundokuRepository.getBooks().collect {
                _tsundokuState.value = TsundokuMainScreenState(
                    tsundokus = it,
                    isLoading = false,
                )
            }
        }
    }
}
