package br.com.rogalabs.postsapi.view

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rogalabs.postsapi.R
import br.com.rogalabs.postsapi.contract.DetailsContract
import br.com.rogalabs.postsapi.model.Comments
import br.com.rogalabs.postsapi.presenter.ListCommentsPresenter
import br.com.rogalabs.postsapi.recyclerview.RecyclerViewListComments
import br.com.rogalabs.postsapi.util.AsycCircular
import kotlinx.android.synthetic.main.activity_list_comment.*
import kotlinx.android.synthetic.main.toolbar.*

class ListComment : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_comment)

        val idPost = intent.getStringExtra("idPost").toInt()
        val postTitle = intent.getStringExtra("postTitle").toString()
        val complementTitle = "O que a galera comentou sobre $postTitle"
        titleActionBar.text = resources.getString(R.string.postagem_recente)
        headerRecyclerComments.text = complementTitle
        setSupportActionBar(toolbar)


        GetComments(this, recyclerviewListComments, idPost).execute()
    }

    internal class GetComments internal constructor
    (activity: ListComment, recyclerView: RecyclerView, idPost: Int)
        : AsyncTask<Void, Void, Boolean>(), DetailsContract.ListCommentsView {

        private val mRecyclerview = recyclerView
        private val mActivity = activity
        private val id = idPost
        private val mProgressBar = ProgressBar(mActivity)
        private lateinit var mCommentAdapter: RecyclerViewListComments
        private lateinit var mPresenter: ListCommentsPresenter

        override fun doInBackground(vararg p0: Void): Boolean {
            var result = false
            try {
                initRecyclerView()
                mPresenter = ListCommentsPresenter()
                mPresenter.setView(this)
                mPresenter.getPost(id)
                result = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }

        private fun initRecyclerView() {
            mRecyclerview.apply {
                layoutManager = LinearLayoutManager(mActivity)
                mCommentAdapter = RecyclerViewListComments()
                adapter = mCommentAdapter
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
                    val mListPosts = mCommentAdapter.getListItems()
                    initRecyclerView()
                    mCommentAdapter.submitList(mListPosts)
                }
                false -> {
                    Toast.makeText(mActivity, R.string.generic_message, Toast.LENGTH_LONG).show()
                }
            }
        }

        override fun showComments(listComments: List<Comments>) {
            mCommentAdapter.submitList(listComments)
        }
    }

}

