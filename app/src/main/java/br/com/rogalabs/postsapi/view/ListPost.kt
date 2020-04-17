package br.com.rogalabs.postsapi.view

import android.os.AsyncTask
import android.os.Bundle
import android.view.Gravity
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
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
        GetPost(this, recyclerviewListPost, circularBar).execute()
    }

    internal class GetPost internal constructor
    (activity: ListPost, recyclerView: RecyclerView, progressBar: ProgressBar)
        : AsyncTask<Void, Void, Boolean>(), PostContract.ListPostView {

        private val mRecyclerview = recyclerView
        private val mActivity = activity
        private val mProgressBar = ProgressBar(mActivity)
        private lateinit var postAdapter: RecyclerViewListPost
        private lateinit var presenter: ListPostPresenter

        override fun doInBackground(vararg p0: Void): Boolean {
            var result = false
            try {
                initRecyclerView()
                presenter = ListPostPresenter()
                presenter.setView(this)
                presenter.getPost()
                result = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }

        private fun initRecyclerView() {
            mRecyclerview.apply {
                layoutManager = LinearLayoutManager(mActivity)
                postAdapter = RecyclerViewListPost()
                adapter = postAdapter
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
                    val mListPosts = postAdapter.getListItems()
                    initRecyclerView()
                    postAdapter.submitList(mListPosts)
                }
                false -> {
                    Toast.makeText(mActivity, R.string.generic_message, Toast.LENGTH_LONG).show()
                }
            }
        }

        override fun showPosts(listPost: List<Posts>) {
            postAdapter.submitList(listPost)
        }
    }

}
