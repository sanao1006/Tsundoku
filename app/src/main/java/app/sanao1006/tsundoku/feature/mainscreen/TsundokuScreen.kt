package app.sanao1006.tsundoku.feature.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.sanao1006.tsundoku.R
import kotlinx.coroutines.launch

@Composable
fun TsundokuScreen(
    viewModel: TsundokuScreenViewModel,
    onFabClick: () -> Unit,
    onItemClick: (id: Int) -> Unit,
) {
    val state by viewModel.tsundokuState.collectAsState()
    val rememberCoroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tsundoku") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (state.tsundokus.isEmpty()) {
                Text(stringResource(R.string.description_when_tsundoku_empty))
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(
                        vertical = 8.dp,
                        horizontal = 8.dp
                    )
                ) {
                    items(state.tsundokus) { book ->
                        TsundokuItem(
                            book = book,
                            onDeleteButtonClick = {
                                rememberCoroutineScope.launch { viewModel.deleteBook(bookId = book.id) }
                            },
                            onItemClick = { id -> onItemClick(id) }
                        )
                    }
                }
            }
            if (state.isLoading) CircularProgressIndicator()
            if (!state.error.isNullOrEmpty()) Text(state.error ?: "")
        }
    }
}
