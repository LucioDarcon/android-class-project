package br.com.rogalabs.postsapi.contract

import br.com.rogalabs.postsapi.model.Posts

interface PostContract {

    interface ListPostView{
        fun showPosts(listPost: List<Posts>)
    }

    interface PostContractPresenter{
        fun setView(view: ListPostView)
        fun getPost()
    }

}