package com.cstasenko.mixclouddiscover.model

data class MixcloudShow(
    val key: String,
    val name: String,
    val link: String,
    val imageUrl: String,
    val user: User
)

data class User(
    val userName: String,
    val userAvatarUrl: String
)