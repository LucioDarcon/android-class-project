package br.com.rogalabs.postsapi.holder

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.model.Posts
import br.com.rogalabs.postsapi.view.ListComment
import kotlinx.android.synthetic.main.recyclerview_list_post.view.*

class ViewHolderPosts constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val postsTitle: TextView = itemView.titleListPost
    private val postsBody: TextView = itemView.bodyListPost

    fun bind(posts: Posts){

        postsTitle.text = posts.title
        postsBody.text = posts.body

        itemView.setOnClickListener {
            val i = Intent(itemView.context, ListComment::class.java)
            i.putExtra("idPost", posts.id.toString())
            i.putExtra("postTitle", posts.title.toString())
            ContextCompat.startActivity(itemView.context, i, null)
        }
    }


}