package com.higor.usertwitter.domain.repository

import com.higor.usertwitter.domain.model.User


internal interface UserRepository {
    fun getIdByUser(userName : String) : User
}