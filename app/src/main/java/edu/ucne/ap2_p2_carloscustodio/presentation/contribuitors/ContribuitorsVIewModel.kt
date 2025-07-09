package edu.ucne.ap2_p2_carloscustodio.presentation.ApiEjemplo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.ap2_p2_carloscustodio.data.repository.repository.ContributorsRepository
import edu.ucne.ap2_p2_carloscustodio.presentation.contribuitors.ContributorsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContributorsViewModel @Inject constructor(
    private val repository: ContributorsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContributorsUiState())
    val uiState: StateFlow<ContributorsUiState> = _uiState.asStateFlow()

    fun getContributors(owner: String, repo: String) {
        viewModelScope.launch {
            repository.getContributors(owner, repo).collect { resource ->
                when (resource) {
                    is edu.ucne.ap2_p2_carloscustodio.presentation.remote.Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
                    }
                    is edu.ucne.ap2_p2_carloscustodio.presentation.remote.Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            contributors = resource.data ?: emptyList(),
                            errorMessage = null
                        )
                    }
                    is edu.ucne.ap2_p2_carloscustodio.presentation.remote.Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = resource.message ?: "Error desconocido"
                        )
                    }
                }
            }
        }
    }
}