package br.com.rogalabs.postsapi.network

import br.com.rogalabs.postsapi.model.Comments
import br.com.rogalabs.postsapi.model.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoints {

    @GET("/posts")
    fun getPosts(): Call<List<Posts>>

    @GET("{id}/comments")
    fun getComments(@Path("id") id: Int): Call<List<Comments>>
}