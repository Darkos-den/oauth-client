package com.darkos.oauth.client

import com.darkos.oauth.engine.BaseEngine
import com.darkos.oauth.engine.OAuthEngine
import com.darkos.oauth.scope.OAuthScope
import com.darkos.oauth.scope.ScopesConfig

@OAuthDsl
class OAuthClientConfig<E: BaseEngine, C: BaseEngine.Config>(
    private val engineBuilder: OAuthEngine<E, C>
){
    private var engineConfig: C.()->Unit = {}
    private val scopesConfig = ScopesConfig()
    internal val scopes: List<OAuthScope>
        get() = scopesConfig.scopes

    fun engine(block: C.()->Unit){
        engineConfig = block
    }

    fun scopes(config: ScopesConfig){
        scopesConfig.apply(config)
    }

    fun scopes(block: ScopesConfig.()->Unit){
        scopesConfig.apply(ScopesConfig().apply(block))
    }

    val engine: E
        get() = engineBuilder.prepare(engineConfig)
}