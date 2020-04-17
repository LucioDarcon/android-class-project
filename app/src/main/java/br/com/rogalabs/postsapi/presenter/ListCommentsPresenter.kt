package br.com.rogalabs.postsapi.presenter

import br.com.rogalabs.postsapi.contract.DetailsContract
import br.com.rogalabs.postsapi.model.Comments
import br.com.rogalabs.postsapi.network.EndPoints
import br.com.rogalabs.postsapi.util.HOSTS
import br.com.rogalabs.postsapi.util.NetworkUtil

class ListCommentsPresenter : DetailsContract.PostContractPresenter {

    private lateinit var view: DetailsContract.ListCommentsView
    lateinit var mListComments: List<Comments>

    override fun setView(view: DetailsContract.ListCommentsView) {
        this.view = view
    }

    override fun getPost(idPost: Int) {
        val retrofitClient = NetworkUtil.getRetrofitInstance(HOSTS.HOST.COMMENTS)
        val endpoint = retrofitClient.create(EndPoints::class.java)
        val callback = endpoint.getComments(idPost)

        val response = callback.execute()
        if(response.body() != null){
            response.body()?.let {
                mListComments = it
            }
            view.showComments(mListComments)
        }

    }
}