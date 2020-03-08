package com.example.hiroki_kawada.githubrepositories.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Repositoriesレスポンスデータ
 */
@JsonClass(generateAdapter = true)
data class GitHubRepositoriesResponse(
    val id: String,
    val name: String,
    @Json(name = "full_name") val fullName: String,
    val url: String
)