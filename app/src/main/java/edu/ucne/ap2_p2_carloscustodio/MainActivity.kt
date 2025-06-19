package edu.ucne.ap2_p2_carloscustodio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.ap2_p2_carloscustodio.presentation.ApiEjemplo.ApiViewModel
import edu.ucne.ap2_p2_carloscustodio.presentation.navigation.ApiNavHost
import edu.ucne.ap2_p2_carloscustodio.ui.theme.Ap2_P2_CarlosCustodioTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ap2_P2_CarlosCustodioTheme {
                val navController = rememberNavController()
                val ApiViewModel: ApiViewModel = hiltViewModel()


                ApiNavHost(
                    navHostController = navController,
                    apiViewModel = ApiViewModel,

                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ap2_P2_CarlosCustodioTheme {
        Greeting("Android")
    }
}