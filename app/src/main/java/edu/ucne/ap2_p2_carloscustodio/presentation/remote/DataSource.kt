package edu.ucne.ap2_p2_carloscustodio.presentation.remote

import edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto.ContributorDto
import edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto.RepositoryDto
import javax.inject.Inject

class DataSource @Inject constructor(
    private val api: GitHubApi
){
    suspend fun listRepos(): List<RepositoryDto> {
        val response = api.listRepos()
        if(response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error: ${response.code()} ${response.message()}")
        }
    }

    suspend fun getContributors(owner: String, repo: String): List<ContributorDto> {
        val response = api.getContributors(owner, repo)
        if(response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error: ${response.code()} ${response.message()}")
        }
    }

}