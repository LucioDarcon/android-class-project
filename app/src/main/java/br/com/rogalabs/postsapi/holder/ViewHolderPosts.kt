package br.com.rogalabs.postsapi.holder

import android.view.View
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.model.Posts
import kotlinx.android.synthetic.main.recyclerview_list_post.view.*

class ViewHolderPosts constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val postsTitle: TextView = itemView.titleListPost
    private val postsBody: TextView = itemView.bodyListPost

    fun bind(posts: Posts){

        postsTitle.text = posts.title
        postsBody.text = posts.body

    }

}