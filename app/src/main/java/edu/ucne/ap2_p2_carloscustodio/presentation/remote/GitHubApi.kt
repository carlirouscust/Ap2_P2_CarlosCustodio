package edu.ucne.ap2_p2_carloscustodio.presentation.remote

import edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto.RepositoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/enelramon/repos")
    suspend fun listRepos(): Response<List<RepositoryDto>>
}