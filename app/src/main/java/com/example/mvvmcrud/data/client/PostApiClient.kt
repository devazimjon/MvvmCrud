package com.example.mvvmcrud.data.client

import com.example.mvvmcrud.data.api.PostApi
import com.example.mvvmcrud.data.model.PostRequest
import com.example.mvvmcrud.data.model.PostResponse
import com.example.mvvmcrud.util.RouteConstants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType


class PostApiClient(
    private val client: HttpClient
) : PostApi {

    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get { url(RouteConstants.GET_URL) }.body()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun createPost(post: PostRequest): PostResponse? {
        return try {
            client.post {
                url(RouteConstants.GET_URL)
                contentType(ContentType.Application.Json)
                setBody(post)
            }.body<PostResponse>()
        } catch (e: Exception) {
            null
        }
    }
}