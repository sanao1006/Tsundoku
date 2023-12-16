package app.sanao1006.tsundoku.presentation.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.sanao1006.tsundoku.presentation.mainscreen.TsundokuScreenViewModel

@Composable
fun TsundokuCreateScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Book") },
                navigationIcon = {
                    IconButton(onClick = onClick) {
                        Icon(Icons.Default.ArrowBack, "")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding))
    }
}
