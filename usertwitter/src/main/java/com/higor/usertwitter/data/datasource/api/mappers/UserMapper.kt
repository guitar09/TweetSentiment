package com.higor.usertwitter.data.datasource.api.mappers


import com.higor.usertwitter.data.datasource.api.model.dto.UserDTO
import com.higor.usertwitter.domain.model.User


fun UserDTO.toDomainUserResponse() = User(id = this.id, name = this.name, username = this.username)