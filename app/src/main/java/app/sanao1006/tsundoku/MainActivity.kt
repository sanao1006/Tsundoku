package app.sanao1006.tsundoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.sanao1006.tsundoku.data.desiginsystem.TsundokuTheme
import app.sanao1006.tsundoku.data.model.InputForCreateTsundoku
import app.sanao1006.tsundoku.feature.create.TsundokuCreateScreen
import app.sanao1006.tsundoku.feature.create.TsundokuCreateViewModel
import app.sanao1006.tsundoku.feature.detail.TsundokuDetailScreen
import app.sanao1006.tsundoku.feature.mainscreen.TsundokuScreen
import app.sanao1006.tsundoku.feature.mainscreen.TsundokuScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TsundokuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TsundokuApp()
                }
            }
        }
    }
}

@Composable
private fun TsundokuApp(
    tsundokuScreenViewModel: TsundokuScreenViewModel = hiltViewModel(),
    tsundokuCreateViewModel: TsundokuCreateViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "tsundokus") {
        composable(route = "tsundokus") {
            TsundokuScreen(
                viewModel = tsundokuScreenViewModel,
                onFabClick = { navController.navigate("create") },
                onItemClick = { navController.navigate("books/$it") }
            )
        }
        composable(
            route = "books/{book_id}",
            arguments = listOf(
                navArgument("book_id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val bookId = entry.arguments?.getInt("book_id")
            TsundokuDetailScreen()
        }
        composable("create") {
            val title by tsundokuCreateViewModel.title.collectAsState()
            val description by tsundokuCreateViewModel.description.collectAsState()
            val totalPage by tsundokuCreateViewModel.totalPage.collectAsState()
            TsundokuCreateScreen(
                input = InputForCreateTsundoku(
                    title = title,
                    description = description,
                    totalPage = totalPage,
                ),
                onBackButtonClick = { navController.popBackStack() },
                onTitleValueChange = tsundokuCreateViewModel::onTitleValueChange,
                onDescriptionValueChange = tsundokuCreateViewModel::onDescriptionValueChange,
                onTotalPageValueChange = tsundokuCreateViewModel::onTotalPageValueChange,
                onCreateButtonClick = {
                    tsundokuCreateViewModel.insertTsundoku()
                    navController.popBackStack()
                }
            )
        }
    }
}
