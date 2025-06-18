package edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto

import com.squareup.moshi.Json

data class RepositoryDto(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String?,
    @Json(name = "html_url") val htmlUrl: String
)