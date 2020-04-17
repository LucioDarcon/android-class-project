package br.com.rogalabs.postsapi.model

data class Details(val id: Int, val postId: Int, val name: String, val email: String, val body: String) {


    override fun toString(): String {
        return "Details(id=$id, postId=$postId, name='$name', email='$email', body='$body')"
    }
}