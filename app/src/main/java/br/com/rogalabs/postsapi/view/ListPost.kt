package br.com.rogalabs.postsapi.view

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.R
import br.com.rogalabs.postsapi.contract.PostContract
import br.com.rogalabs.postsapi.model.Posts
import br.com.rogalabs.postsapi.presenter.ListPostPresenter
import br.com.rogalabs.postsapi.recyclerview.RecyclerViewListPost
import br.com.rogalabs.postsapi.util.AsycCircular
import kotlinx.android.synthetic.main.activity_list_post.*
import kotlinx.android.synthetic.main.toolbar.*

class ListPost : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_post)

        titleActionBar.text = resources.getString(R.string.postagem_recente)
        setSupportActionBar(toolbar)
        GetPost(this, recyclerviewListPost).execute()
    }

    internal class GetPost internal constructor
    (activity: ListPost, recyclerView: RecyclerView)
        : AsyncTask<Void, Void, Boolean>(), PostContract.ListPostView {

        private val mRecyclerview = recyclerView
        private val mActivity = activity
        private val mProgressBar = ProgressBar(mActivity)
        private lateinit var mPostAdapter: RecyclerViewListPost
        private lateinit var mPresenter: ListPostPresenter

        override fun doInBackground(vararg p0: Void): Boolean {
            var result = false
            try {
                initRecyclerView()
                mPresenter = ListPostPresenter()
                mPresenter.setView(this)
                mPresenter.getPost()
                result = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }

        private fun initRecyclerView() {
            mRecyclerview.apply {
                layoutManager = LinearLayoutManager(mActivity)
                mPostAdapter = RecyclerViewListPost()
                adapter = mPostAdapter
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()
            AsycCircular(mProgressBar).execute()
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)
            when (result) {
                true -> {
                    val mListPosts = mPostAdapter.getListItems()
                    initRecyclerView()
                    mPostAdapter.submitList(mListPosts)
                }
                false -> {
                    Toast.makeText(mActivity, R.string.generic_message, Toast.LENGTH_LONG).show()
                }
            }
        }

        override fun showPosts(listPost: List<Posts>) {
            mPostAdapter.submitList(listPost)
        }
    }

}
