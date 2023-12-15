package app.sanao1006.tsundoku.presentation.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.sanao1006.tsundoku.data.desiginsystem.TsundokuTheme
import app.sanao1006.tsundoku.data.model.Book

@Composable
fun TsundokuScreen(
    state: TsundokuMainScreenState,
    onItemClick: (id: Int) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 8.dp,
                horizontal = 8.dp
            )
        ) {
            items(state.tsundokus) { book ->
                TsundokuItem(
                    book = book,
                    onItemClick = { id -> onItemClick(id) }
                )
            }
        }
        if (state.isLoading) CircularProgressIndicator()
        if (!state.error.isNullOrEmpty()) Text(state.error)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TsundokuTheme {
        val dummyBooks = listOf(
            Book(0, "技術書0", "web", 100, createAt = "2023-07-15", updatedAt = "2023-12-12"),
            Book(1, "技術書1", "css", 150, createAt = "2023-07-10", updatedAt = "2023-12-11"),
            Book(2, "技術書2", "kotlin", 235, createAt = "2023-06-01", updatedAt = "2023-11-12"),
            Book(3, "技術書3", "Android", 300, createAt = "2023-03-26", updatedAt = "2023-11-25")
        )
        TsundokuScreen(
            state = TsundokuMainScreenState(tsundokus = dummyBooks, isLoading = false),
            onItemClick = { },
        )
    }
}
