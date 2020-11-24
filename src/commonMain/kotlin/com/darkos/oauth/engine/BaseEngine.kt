package com.darkos.oauth.engine

import com.darkos.oauth.client.OAuthDsl
import com.darkos.oauth.scope.OAuthScope

abstract class BaseEngine{
    sealed class Result{
        object Canceled: Result()
        class Success(val token: String): Result()
        class Error(val e: Exception): Result()
    }

    abstract fun applyScopes(scopes: List<OAuthScope>)
    abstract suspend fun getAccessToken(): Result

    @OAuthDsl
    abstract class Config
}