package app.sanao1006.tsundoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.sanao1006.tsundoku.data.desiginsystem.TsundokuTheme
import app.sanao1006.tsundoku.presentation.mainscreen.TsundokuScreen
import app.sanao1006.tsundoku.presentation.mainscreen.TsundokuScreenViewModel
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
private fun TsundokuApp(viewModel: TsundokuScreenViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "tsundokus") {
        composable(route = "tsundokus") {
            TsundokuScreen(
                viewModel = viewModel,
                onFabClick = { },
                onItemClick = { }
            )
        }
    }
}
