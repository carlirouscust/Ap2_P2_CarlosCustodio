package edu.ucne.ap2_p2_carloscustodio.data.repository.repository

import edu.ucne.ap2_p2_carloscustodio.presentation.remote.DataSource
import edu.ucne.ap2_p2_carloscustodio.presentation.remote.Resource
import edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto.RepositoryDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val dataSource: DataSource
) {
    fun getApi(): Flow<Resource<List<RepositoryDto>>> = flow {
        try {
            emit(Resource.Loading())
            val repos = dataSource.listRepos()
            emit(Resource.Success(repos))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.message}"))
        }
    }
}