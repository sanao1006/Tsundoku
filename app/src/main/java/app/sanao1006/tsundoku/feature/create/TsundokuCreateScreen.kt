package app.sanao1006.tsundoku.feature.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.sanao1006.tsundoku.data.desiginsystem.TsundokuTheme
import app.sanao1006.tsundoku.data.model.InputForCreateTsundoku
import io.sanao1006.tsundoku.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TsundokuCreateScreen(
    input: InputForCreateTsundoku,
    onBackButtonClick: () -> Unit,
    onCreateButtonClick: () -> Unit,
    onTitleValueChange: (String) -> Unit,
    onDescriptionValueChange: (String) -> Unit,
    onTotalPageValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_create_tsundoku)) },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(Icons.Default.ArrowBack, "")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            Column {
                OutlinedTextField(
                    value = input.title,
                    onValueChange = onTitleValueChange,
                    label = { Text(stringResource(R.string.pref_create_tsundoku_title)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = input.description,
                    onValueChange = onDescriptionValueChange,
                    label = { Text(stringResource(R.string.pref_create_tsundoku_description)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = input.totalPage,
                    onValueChange = onTotalPageValueChange,
                    label = { Text(stringResource(R.string.pref_create_tsundoku_page_count)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    enabled = !input.title.isNullOrBlank() && !input.totalPage.isNullOrBlank(),
                    onClick = onCreateButtonClick
                ) {
                    Icon(Icons.Default.Done, "")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(stringResource(R.string.create_tsundoku))
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "テキストあり")
@Composable
fun PreviewTsundokuCreateScreen() {
    TsundokuTheme {
        TsundokuCreateScreen(
            input = InputForCreateTsundoku(
                title = "タイトル",
                description = "",
                totalPage = "200",
            ),
            onBackButtonClick = {},
            onCreateButtonClick = {},
            onTitleValueChange = {},
            onDescriptionValueChange = {},
            onTotalPageValueChange = {}
        )
    }
}

@Preview(showBackground = true, name = "テキストなし")
@Composable
fun PreviewTsundokuCreateScreenWithoutText() {
    TsundokuTheme {
        TsundokuCreateScreen(
            input = InputForCreateTsundoku(
                title = "",
                description = "",
                totalPage = "",
            ),
            onBackButtonClick = {},
            onCreateButtonClick = {},
            onTitleValueChange = {},
            onDescriptionValueChange = {},
            onTotalPageValueChange = {}
        )
    }
}
