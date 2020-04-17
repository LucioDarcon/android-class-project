package br.com.rogalabs.postsapi.model

import com.google.gson.annotations.SerializedName

data class Posts(
        @SerializedName("userId")
        val userId: Int = 0,
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("title")
        val title: String = "",
        @SerializedName("body")
        val body: String = ""
) {

    override fun toString(): String {
        return "Posts(userId=$userId, id=$id, title='$title', body='$body')"
    }
}