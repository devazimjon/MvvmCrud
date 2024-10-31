package com.example.mvvmcrud.data.api

import com.example.mvvmcrud.data.client.PostApiClient
import com.example.mvvmcrud.data.model.PostRequest
import com.example.mvvmcrud.data.model.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

interface PostApi {

    suspend fun getPosts(): List<PostResponse>

    suspend fun createPost(post: PostRequest): PostResponse?

    companion object {
        fun createPostClient(): PostApi {
            return PostApiClient(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                        })
                    }
                }
            )
        }
    }
}
