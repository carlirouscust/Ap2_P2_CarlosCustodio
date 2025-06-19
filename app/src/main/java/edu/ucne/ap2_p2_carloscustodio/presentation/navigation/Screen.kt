package edu.ucne.ap2_p2_carloscustodio.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object ApiListScreen : Screen()
    @Serializable
    data class ApiScreen(val name: String?) : Screen()
}