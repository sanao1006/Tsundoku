package app.sanao1006.tsundoku.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.sanao1006.tsundoku.data.desiginsystem.Typography
import app.sanao1006.tsundoku.data.model.Book
import app.sanao1006.tsundoku.data.model.Comment
import io.sanao1006.tsundoku.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateProgressField(
    onCommentTextChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    book: Book,
    commentText: String,
    comments: List<Comment>
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val isShowCreateCommentField = remember { mutableStateOf(false) }

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
                label = { Text(stringResource(id = R.string.progress_nowpage)) },
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
                label = { Text(text = stringResource(id = R.string.progress_chiken)) },
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
        if (comments.isNotEmpty()) {
            ProgressList(comments = comments)
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}
