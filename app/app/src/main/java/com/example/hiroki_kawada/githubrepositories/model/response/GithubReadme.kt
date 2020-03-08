package com.example.hiroki_kawada.githubrepositories.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Readmeレスポンスデータ
 */
@JsonClass(generateAdapter = true)
data class GithubReadme(
    @Json(name = "html_url") val htmlUrl: String
)