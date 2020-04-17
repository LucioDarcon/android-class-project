package br.com.rogalabs.postsapi.presenter

import br.com.rogalabs.postsapi.contract.PostContract
import br.com.rogalabs.postsapi.model.Posts
import br.com.rogalabs.postsapi.network.EndPoints
import br.com.rogalabs.postsapi.util.HOSTS
import br.com.rogalabs.postsapi.util.NetworkUtil

class ListPostPresenter : PostContract.PostContractPresenter {

    private lateinit var view: PostContract.ListPostView
    lateinit var mListService: List<Posts>

    override fun setView(view: PostContract.ListPostView) {
        this.view = view
    }

    override fun getPost() {
        val retrofitClient = NetworkUtil.getRetrofitInstance(HOSTS.HOST.POSTS)
        val endpoint = retrofitClient.create(EndPoints::class.java)
        val callback = endpoint.getPosts()

        val response = callback.execute()
        if(response.body() != null){
            val list = response.body()?.let {
                mListService = it
            }
            view.showPosts(mListService)
        }
    }


}