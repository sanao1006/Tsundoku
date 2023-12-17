package app.sanao1006.tsundoku.domain

import app.sanao1006.tsundoku.data.db.BookEntity
import app.sanao1006.tsundoku.data.di.TsundokuRepository
import javax.inject.Inject

class InsertTsundokuUseCase @Inject constructor(
    private val tsundokuRepository: TsundokuRepository
) {
    suspend operator fun invoke(
        title: String,
        description: String,
        totalPage: Int,
        createAt: String,
        updatedAt: String
    ) {
        tsundokuRepository.insertBook(
            BookEntity(
                id = 0,
                title = title,
                description = description,
                totalPage = totalPage,
                createdAt = createAt,
                updatedAt = updatedAt
            )
        )
    }
}
