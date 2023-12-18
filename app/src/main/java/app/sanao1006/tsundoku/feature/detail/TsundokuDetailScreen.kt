package app.sanao1006.tsundoku.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.sanao1006.tsundoku.data.desiginsystem.Typography
import app.sanao1006.tsundoku.data.model.Book
import io.sanao1006.tsundoku.R
import kotlin.math.floor

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
        Box(modifier = modifier.padding(innerPadding)) {
            Column(
                modifier = modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Default.MenuBook, "")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = book.title, style = Typography.h1)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Column(horizontalAlignment = Alignment.Start) {
                        if (!book.description.isNullOrBlank()) {
                            Text(text = "説明", fontWeight = FontWeight.Bold)
                            Text(book.description)
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(text = "進捗率", fontWeight = FontWeight.Bold)
                            Text("${floor(2.0 / book.totalPage.toDouble())}% (現在: ${book.nowPage}, 総ページ数: ${book.totalPage})")
                            Spacer(modifier = Modifier.size(16.dp))
                            Text("最終更新: ${book.updatedAt}")
                            Text("登録日: ${book.createAt}")
                        }
                    }
                }
            }
        }
    }
}
