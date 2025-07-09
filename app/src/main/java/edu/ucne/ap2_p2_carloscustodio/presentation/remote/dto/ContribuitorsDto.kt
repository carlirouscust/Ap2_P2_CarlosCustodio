package edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto

import com.squareup.moshi.Json

data class ContributorDto(
    @Json(name = "login") val login: String,
    @Json(name = "avatar_url") val avatarUrl: String?,
    @Json(name = "contributions") val contributions: Int
)
