package com.example.hiroki_kawada.githubrepositories.model.response

import com.example.hiroki_kawada.githubrepositories.R

/**
 * エラーレスポンスオブジェクト
 */
object ErrorResponse {

    var code: String = ""
    var errorMassage: String = ""

    fun createTimeoutError(): ErrorResponse {
        val timeoutErrorResponse = ErrorResponse
        timeoutErrorResponse.code = "504"
        timeoutErrorResponse.errorMassage = "タイムアウトしました。"

        return timeoutErrorResponse
    }


    fun createNetworkError(): ErrorResponse {
        val timeoutErrorResponse = ErrorResponse
        timeoutErrorResponse.code = "505"
        timeoutErrorResponse.errorMassage = "表示に必要な情報が取得できませんでした。"

        return timeoutErrorResponse
    }
}