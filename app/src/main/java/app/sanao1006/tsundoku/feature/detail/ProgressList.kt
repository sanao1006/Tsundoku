package app.sanao1006.tsundoku.feature.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.sanao1006.tsundoku.data.model.Comment

@Composable
fun ProgressList(modifier: Modifier = Modifier, comments: List<Comment>) {
    LazyColumn(
        modifier = modifier
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(4.dp),
                color = Color(0xFF18864B)
            )
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
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
}
