package edu.ucne.ap2_p2_carloscustodio.data.local.repository

import edu.ucne.ap2_p2_carloscustodio.presentation.remote.DataSource
import edu.ucne.ap2_p2_carloscustodio.presentation.remote.Resource
import edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto.RepositoryDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val dataSource: DataSource
) {
    fun getApi(username: String): Flow<Resource<List<RepositoryDto>>> = flow {
        try {
            emit(Resource.Loading())
            val Api = dataSource.getApi(username)
            emit(Resource.Success(Api))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.message}"))
        }
    }
}