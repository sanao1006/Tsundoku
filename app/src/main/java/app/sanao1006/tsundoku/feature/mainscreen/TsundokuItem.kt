package app.sanao1006.tsundoku.feature.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import app.sanao1006.tsundoku.data.model.Book

@Composable
fun TsundokuItem(
    book: Book,
    onDeleteButtonClick: () -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(book.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            TsundokuIcon(Icons.Default.MenuBook, Modifier.weight(0.15f))
            TsundokuInfo(
                truncateString(book.title, maxLength = 23),
                truncateString(book.description),
                Modifier.weight(0.7f)
            )
            TsundokuIcon(
                icon = Icons.Default.Delete,
                onIconClick = onDeleteButtonClick,
                modifier = Modifier.weight(0.15f)
            )
        }
    }
}

@Composable
fun TsundokuIcon(icon: ImageVector, modifier: Modifier, onIconClick: () -> Unit = { }) {
    Image(
        imageVector = icon,
        contentDescription = "",
        modifier = modifier
            .padding(8.dp)
            .clickable { onIconClick() }
    )
}

private fun truncateString(input: String, maxLength: Int = 32): String {
    return if (input.length <= maxLength) {
        input
    } else {
        input.substring(0, maxLength) + "..."
    }
}
