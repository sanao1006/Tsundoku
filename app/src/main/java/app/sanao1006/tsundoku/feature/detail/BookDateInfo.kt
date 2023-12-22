package app.sanao1006.tsundoku.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.sanao1006.tsundoku.data.model.Book
import io.sanao1006.tsundoku.R

@Composable
fun BookDateInfo(modifier: Modifier = Modifier, book: Book) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(stringResource(id = R.string.date_last_updated, book.updatedAt))
        Text(stringResource(id = R.string.date_created, book.createAt))
    }
}
