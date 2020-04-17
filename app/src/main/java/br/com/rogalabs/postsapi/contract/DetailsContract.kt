package br.com.rogalabs.postsapi.contract

import br.com.rogalabs.postsapi.model.Comments

interface DetailsContract {

    interface ListCommentsView{
        fun showComments(listComments: List<Comments>)
    }

    interface PostContractPresenter{
        fun setView(view: ListCommentsView)
        fun getPost(idPost: Int)
    }

}