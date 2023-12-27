package app.sanao1006.tsundoku.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.sanao1006.tsundoku.R

@Composable
fun TsundokuDetailScreen(
    viewModel: TsundokuDetailScreenViewModel,
    bookId: Int,
    onBackButtonClick: () -> Unit,
    onButtonClick: () -> Unit,
    onCommentTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val book by viewModel.book.collectAsState()
    val commentText by viewModel.commentText.collectAsState()
    val comments by viewModel.comments.collectAsState()
    DisposableEffect(Unit) {
        viewModel.getBook(bookId)
        viewModel.getCommentsByBookId(bookId)
        onDispose { }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_detail_tsundoku)) },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(Icons.Default.ArrowBack, "")
                    }
                },
                backgroundColor = Color(0xfff2fbff)
            )
        },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BookInfo(book = book)
                CreateProgressField(
                    onCommentTextChange = onCommentTextChange,
                    onButtonClick = onButtonClick,
                    book = book,
                    commentText = commentText,
                    comments = comments
                )
                BookDateInfo(book = book)
            }
        }
    }
}
