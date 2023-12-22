package app.sanao1006.tsundoku.domain

import app.sanao1006.tsundoku.data.db.CommentEntity
import app.sanao1006.tsundoku.data.di.TsundokuRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class InsertCommentUseCase @Inject constructor(
    private val tsundokuRepository: TsundokuRepository
) {
    suspend operator fun invoke(id: Int, comment: String) {
        tsundokuRepository.insertComment(
            CommentEntity(
                commentId = 0,
                id = id,
                comment = comment,
                updatedAt = getCurrentTime(),
                createdAt = getCurrentTime()
            )
        )
    }
}

private fun getCurrentTime(): String {
    val currentInstant = Clock.System.now()
    val currentLocalDateTIme = currentInstant.toLocalDateTime(TimeZone.currentSystemDefault())
    return currentLocalDateTIme.date.toString()
}
