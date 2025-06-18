package edu.ucne.ap2_p2_carloscustodio.presentation.remote

import edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto.RepositoryDto
import javax.inject.Inject

class DataSource @Inject constructor(
    private val api: GitHubApi
){
    suspend fun getApi(username: String): List<RepositoryDto> = api.listRepos(username)

}