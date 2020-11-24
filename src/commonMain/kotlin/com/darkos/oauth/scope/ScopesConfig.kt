package com.darkos.oauth.scope

import com.darkos.oauth.client.OAuthDsl

@OAuthDsl
class ScopesConfig{
    internal var scopes: List<OAuthScope> = emptyList()

    fun addScope(scope: OAuthScope){
        if(scopes.contains(scope).not()){
            scopes = scopes + scope
        }
    }

    internal fun apply(config: ScopesConfig){
        config.scopes.forEach(this::addScope)
    }
}