package com.higor.core.usecase

interface UseCase<T, in Param> {
     fun execute(params : Param) : T
}