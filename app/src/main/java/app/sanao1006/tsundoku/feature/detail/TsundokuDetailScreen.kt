package app.sanao1006.tsundoku.feature.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.sanao1006.tsundoku.data.desiginsystem.Typography
import io.sanao1006.tsundoku.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TsundokuDetailScreen(
    viewModel: TsundokuDetailScreenViewModel,
    bookId: Int,
    onBackButtonClick: () -> Unit,
    onButtonClick: () -> Unit,
    onCommentTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val rememberScrollState = rememberScrollState()

    val book by viewModel.book.collectAsState()
    val commentText by viewModel.commentText.collectAsState()
    val comments by viewModel.comments.collectAsState()
    DisposableEffect(Unit) {
        viewModel.getBook(bookId)
        viewModel.getCommentsByBookId(bookId)
        onDispose { }
    }

    val isShowCreateCommentField = remember { mutableStateOf(false) }
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
                .fillMaxSize()
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
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.book_progress_percent),
                            fontWeight = FontWeight.Bold,
                            style = Typography.h5,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        IconButton(onClick = {
                            isShowCreateCommentField.value = !isShowCreateCommentField.value
                        }) {
                            if (isShowCreateCommentField.value) {
                                Icon(Icons.Default.Cancel, contentDescription = "")
                            } else {
                                Icon(Icons.Default.Create, contentDescription = "")
                            }
                        }
                    }
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
                    if (isShowCreateCommentField.value) {
                        Spacer(modifier = Modifier.size(8.dp))
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            label = { Text("現在のページ数") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.moveFocus(FocusDirection.Next) }
                            )
                        )
                        OutlinedTextField(
                            value = commentText,
                            onValueChange = onCommentTextChange,
                            label = { Text(text = "感想など") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            )
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(
                            onClick = onButtonClick,
                            enabled = !commentText.isNullOrBlank()
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = "")
                        }
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    LazyColumn(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(4.dp),
                                color = Color(0xFF18864B)
                            )
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(comments) {
                            Card(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                elevation = 2.dp
                            ) {
                                Row(modifier = Modifier.padding(8.dp)) {
                                    Text(text = it.comment)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                }
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
