package br.com.rogalabs.postsapi.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.R
import br.com.rogalabs.postsapi.holder.ViewHolderPosts
import br.com.rogalabs.postsapi.model.Posts

class RecyclerViewListPost: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Posts> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderPosts(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_list_post, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolderPosts -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(listPost: List<Posts>){
        this.items = listPost
    }

    fun getListItems(): List<Posts>{
        return this.items
    }

}