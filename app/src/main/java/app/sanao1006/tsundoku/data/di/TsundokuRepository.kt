package app.sanao1006.tsundoku.data.di

import app.sanao1006.tsundoku.data.db.BookDao
import app.sanao1006.tsundoku.data.db.BookEntity
import app.sanao1006.tsundoku.data.model.Book
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TsundokuRepository @Inject constructor(
    private val bookDao: BookDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {
    fun getBooks(): Flow<List<Book>> = bookDao.getBooks().map { it.map(BookEntity::toBook) }

    fun getBook(bookId: Int): Flow<Book> = bookDao.getBook(bookId).map { it.toBook() }

    suspend fun insertBook(book: BookEntity) = withContext(ioDispatcher) {
        bookDao.insertBook(book = book)
    }

    suspend fun updateBookInfo(book: BookEntity) = withContext(ioDispatcher) {
        bookDao.updateBookInfo(book = book)
    }

    suspend fun deleteBook(bookId: Int) = withContext(ioDispatcher) {
        bookDao.deleteBook(bookId = bookId)
    }
}
