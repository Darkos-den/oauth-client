package com.darkos.oauth.client

import com.darkos.oauth.engine.BaseEngine
import com.darkos.oauth.engine.OAuthEngine

class OAuthClient(
    config: OAuthClientConfig<*, *>
){
    private val engine = config.engine

    init {
        engine.applyScopes(config.scopes)
    }

    suspend fun getAccessToken(): String {
        return engine.getAccessToken().let {
            when(it){
                is BaseEngine.Result.Success -> it.token
                is BaseEngine.Result.Canceled -> TODO("throw cancel exception")
                is BaseEngine.Result.Error -> TODO("throw error exception")
            }
        }
    }
}

@OAuthDsl
fun <E: BaseEngine, C: BaseEngine.Config>OAuthClient(
    engine: OAuthEngine<E, C>,
    block: OAuthClientConfig<E, C>.() -> Unit
) = OAuthClient(OAuthClientConfig(engine).apply(block))





