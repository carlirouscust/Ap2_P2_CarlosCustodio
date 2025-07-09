package edu.ucne.ap2_p2_carloscustodio.data.repository.repository

import edu.ucne.ap2_p2_carloscustodio.presentation.remote.DataSource
import edu.ucne.ap2_p2_carloscustodio.presentation.remote.Resource
import edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto.ContributorDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContributorsRepository @Inject constructor(
    private val dataSource: DataSource
) {

    fun getContributors(owner: String, repo: String): Flow<Resource<List<ContributorDto>>> = flow {
        try {
            emit(Resource.Loading())
            val contributors = dataSource.getContributors(owner, repo)
            emit(Resource.Success(contributors))
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.message}"))
        }
    }
}