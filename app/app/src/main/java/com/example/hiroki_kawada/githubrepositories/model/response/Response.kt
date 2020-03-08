package com.example.hiroki_kawada.githubrepositories.model.response

/**
 * レスポンスのラッピングクラス
 *
 * [Success]は成功レスポンス
 * [Failure]はエラーレスポンス
 *
 */
sealed class Response<T> {
    data class Success<T>(val value: T) : Response<T>()
    data class Failure<T>(val value: ErrorResponse) : Response<T>()
}
