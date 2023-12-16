package app.sanao1006.tsundoku.data.di

import app.sanao1006.tsundoku.data.db.BookDao
import app.sanao1006.tsundoku.data.db.BookEntity
import app.sanao1006.tsundoku.data.model.Book
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TsundokuRepository @Inject constructor(
    private val bookDao: BookDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getBooks(): List<Book> {
        return withContext(ioDispatcher) {
            return@withContext bookDao.getBooks().map {
                Book(
                    id = it.id,
                    description = it.description ?: "",
                    title = it.title ?: "",
                    totalPage = it.totalPage,
                    nowPage = it.nowPage,
                    createAt = it.createdAt,
                    updatedAt = it.updatedAt
                )
            }
        }
    }
    suspend fun insertBook(book: BookEntity) = withContext(ioDispatcher) {
        bookDao.insertBook(book = book)
    }

    suspend fun updateBookInfo(book: BookEntity) = withContext(ioDispatcher) {
        bookDao.updateBookInfo(book = book)
    }
}
