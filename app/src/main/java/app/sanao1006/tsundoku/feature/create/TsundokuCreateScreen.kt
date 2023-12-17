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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.sanao1006.tsundoku.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TsundokuCreateScreen(
    onBackButtonClick: () -> Unit,
    onCreateButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TsundokuCreateViewModel = hiltViewModel()
) {
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val totalPage by viewModel.totalPage.collectAsState()

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
                    value = title,
                    onValueChange = { viewModel.onTitleValueChange(it) },
                    label = { Text(stringResource(R.string.pref_create_tsundoku_title)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { viewModel.onDescriptionValueChange(it) },
                    label = { Text(stringResource(R.string.pref_create_tsundoku_description)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = totalPage,
                    onValueChange = { viewModel.onTotalPageValueChange(it) },
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
