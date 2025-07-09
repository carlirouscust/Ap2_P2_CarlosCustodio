package edu.ucne.ap2_p2_carloscustodio.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import edu.ucne.ap2_p2_carloscustodio.presentation.ApiEjemplo.ApiViewModel
import edu.ucne.ap2_p2_carloscustodio.presentation.ApiEjemplo.ApiListScreen
import edu.ucne.ap2_p2_carloscustodio.presentation.contribuitors.ApiListContribuitors

@Composable
fun ApiNavHost(
    navHostController: NavHostController,
    apiViewModel: ApiViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = "ApiList"
    ) {
        // Pantalla principal de lista de repositorios
        composable("ApiList") {
            val uiState by apiViewModel.uiState.collectAsState()

            ApiListScreen(
                state = uiState,
                onCreate = {
                    // Ya no se usa la creación/edición, así que esto se puede dejar vacío o sin acción
                },
                onRepositorySelected = { repo ->
                    // Extrae owner y repoName del htmlUrl
                    val segments = repo.htmlUrl.removePrefix("https://github.com/").split("/")
                    val owner = segments.getOrNull(0) ?: ""
                    val repoName = segments.getOrNull(1) ?: ""
                    navHostController.navigate("Contributors/$owner/$repoName")
                }
            )
        }

        // Pantalla de contribuidores
        composable(
            "Contributors/{owner}/{repoName}",
            arguments = listOf(
                navArgument("owner") { type = NavType.StringType },
                navArgument("repoName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val owner = backStackEntry.arguments?.getString("owner") ?: ""
            val repoName = backStackEntry.arguments?.getString("repoName") ?: ""
            ApiListContribuitors(
                owner = owner,
                repoName = repoName,
                onBack = { navHostController.popBackStack() }
            )
        }
    }
}