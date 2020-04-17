package br.com.rogalabs.postsapi.network

import br.com.rogalabs.postsapi.model.Posts
import retrofit2.Call
import retrofit2.http.GET

interface EndPoints {

    @GET("/posts")
    fun getPosts(): Call<List<Posts>>
}