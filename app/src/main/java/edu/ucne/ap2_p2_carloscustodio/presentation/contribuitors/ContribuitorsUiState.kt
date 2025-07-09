package edu.ucne.ap2_p2_carloscustodio.presentation.contribuitors

import edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto.ContributorDto

data class ContributorsUiState(
    val contributors: List<ContributorDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
