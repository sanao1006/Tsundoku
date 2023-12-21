package app.sanao1006.tsundoku.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.sanao1006.tsundoku.data.desiginsystem.Typography
import app.sanao1006.tsundoku.data.model.Book
import io.sanao1006.tsundoku.R

@Composable
fun TsundokuDetailScreen(
    book: Book,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_detail_tsundoku)) },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(Icons.Default.ArrowBack, "")
                    }
                }
            )
        },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding),
        ) {
            Column(
                modifier = modifier.padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.MenuBook, contentDescription = "",
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.CenterHorizontally)
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
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.book_progress_percent),
                        fontWeight = FontWeight.Bold,
                        style = Typography.h5,
                        textAlign = TextAlign.Center
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(
                                id = R.string.reading_progress,
                                (book.nowPage.toDouble() / book.totalPage.toDouble()) * 100.0
                            ),
                            style = Typography.h6
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = stringResource(
                                id = R.string.book_pages,
                                book.nowPage,
                                book.totalPage.toString()
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(stringResource(id = R.string.date_last_updated, book.updatedAt))
                        Text(stringResource(id = R.string.date_created, book.createAt))
                    }
                }
            }
        }
    }
}
