package br.com.rogalabs.postsapi.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.R
import br.com.rogalabs.postsapi.holder.ViewHolderComments
import br.com.rogalabs.postsapi.model.Comments
import br.com.rogalabs.postsapi.model.Posts

class RecyclerViewListComments: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Comments> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderComments(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_comments, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolderComments -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(listComments: List<Comments>){
        this.items = listComments
    }

    fun getListItems(): List<Comments>{
        return this.items
    }

}