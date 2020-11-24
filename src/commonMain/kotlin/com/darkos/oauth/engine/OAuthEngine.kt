package com.darkos.oauth.engine

interface OAuthEngine<E: BaseEngine, C: BaseEngine.Config>{
    fun prepare(block: C.()->Unit): E
}