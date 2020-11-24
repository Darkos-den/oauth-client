package com.darkos.oauth.scope

import com.darkos.oauth.client.OAuthDsl

@OAuthDsl
fun Scopes(block: ScopesConfig.()->Unit) = ScopesConfig().apply(block)