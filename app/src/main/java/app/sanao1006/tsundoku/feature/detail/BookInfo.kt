package app.sanao1006.tsundoku.feature.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.sanao1006.tsundoku.data.desiginsystem.Typography
import app.sanao1006.tsundoku.data.model.Book
import io.sanao1006.tsundoku.R

@Composable
fun BookInfo(modifier: Modifier = Modifier, book: Book) {
    Icon(
        imageVector = Icons.Default.MenuBook, contentDescription = "",
        modifier = Modifier.size(64.dp)
    )
    Spacer(modifier = Modifier.size(8.dp))
    Column {
        Text(
            text = stringResource(id = R.string.book_title),
            color = Color(0xFF18864B),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = book.title, style = Typography.h1)
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )
    }
    if (!book.description.isNullOrBlank()) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = stringResource(id = R.string.book_description),
                color = Color(0xFF18864B),
                fontWeight = FontWeight.Bold
            )
            Text(book.description)
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
        }
    }

}
