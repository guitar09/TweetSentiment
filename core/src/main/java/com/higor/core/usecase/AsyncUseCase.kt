package com.higor.core.usecase

interface AsyncUseCase<T, in Param> {
    suspend fun execute(params : Param) : T
}