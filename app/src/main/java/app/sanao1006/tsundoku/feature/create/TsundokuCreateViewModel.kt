package app.sanao1006.tsundoku.feature.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sanao1006.tsundoku.domain.InsertTsundokuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

@HiltViewModel
class TsundokuCreateViewModel @Inject constructor(
    private val insertTsundokuUseCase: InsertTsundokuUseCase
) : ViewModel() {
    private val _title = MutableStateFlow("")
    var title = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    var description = _description.asStateFlow()

    private val _totalPage = MutableStateFlow("")
    var totalPage = _totalPage.asStateFlow()

    fun insertTsundoku() = viewModelScope.launch {
        insertTsundokuUseCase(
            title = _title.value,
            description = _description.value,
            totalPage = _totalPage.value.toInt(),
            createAt = getCurrentTime(),
            updatedAt = getCurrentTime(),
        )
        clear()
    }

    private fun clear() {
        _title.value = ""
        _description.value = ""
        _totalPage.value = ""
    }

    fun onTitleValueChange(title: String) {
        _title.value = title
    }

    fun onDescriptionValueChange(des: String) {
        _description.value = des
    }

    fun onTotalPageValueChange(totalPage: String) {
        _totalPage.value = totalPage
    }

    private fun getCurrentTime(): String {
        val currentInstant = Clock.System.now()
        val currentLocalDateTIme = currentInstant.toLocalDateTime(TimeZone.currentSystemDefault())
        return currentLocalDateTIme.date.toString()
    }
}
