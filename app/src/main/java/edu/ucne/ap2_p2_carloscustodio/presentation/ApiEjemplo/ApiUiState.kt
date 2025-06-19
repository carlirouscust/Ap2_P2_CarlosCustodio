package edu.ucne.ap2_p2_carloscustodio.presentation.ApiEjemplo

import edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto.RepositoryDto


data class ApiUiState(
    val name: String = "",
    val description: String = "",
    val htmlUrl: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val api: List<RepositoryDto> = emptyList(),
    val inputError: String? = null
)