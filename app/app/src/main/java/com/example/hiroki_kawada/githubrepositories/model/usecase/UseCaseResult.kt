package com.example.hiroki_kawada.githubrepositories.model.usecase

/**
 * 疑似Eitherクラス
 *
 * [Success]は成功結果。任意のクラスを返却する。
 * [Failure]は失敗結果。任意のクラスを返却する。
 *
 */
sealed class UseCaseResult<T, S> {
    data class Success<T, S>(val value: T) : UseCaseResult<T, S>()
    data class Failure<T, S>(val value: S) : UseCaseResult<T, S>()
}