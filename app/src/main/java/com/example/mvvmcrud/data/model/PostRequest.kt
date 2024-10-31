package com.example.mvvmcrud.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val userId: Int,
    val title: String,
    val body: String,
)