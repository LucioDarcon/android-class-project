package br.com.rogalabs.postsapi.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.model.Comments
import br.com.rogalabs.postsapi.model.Posts
import kotlinx.android.synthetic.main.recyclerview_comments.view.*

class ViewHolderComments constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.name
    private val email: TextView = itemView.email
    private val body: TextView = itemView.bodyListComments

    fun bind(comments: Comments){

        name.text = comments.name
        email.text = comments.email
        body.text = comments.body

    }

}