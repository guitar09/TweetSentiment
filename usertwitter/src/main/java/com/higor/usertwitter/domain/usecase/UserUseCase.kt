package com.higor.usertwitter.domain.usecase

import com.higor.core.usecase.AsyncUseCase
import com.higor.usertwitter.domain.model.User
import com.higor.usertwitter.domain.repository.UserRepository


internal class UserUseCase(private val repository: UserRepository) : AsyncUseCase<User, String> {

    override suspend fun execute(params: String): User {
        return repository.getIdByUser(params)
    }
}