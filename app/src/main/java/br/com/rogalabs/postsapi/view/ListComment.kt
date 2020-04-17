package br.com.rogalabs.postsapi.view

import android.graphics.Color
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
import kotlinx.android.synthetic.main.activity_list_comment.*
import kotlinx.android.synthetic.main.toolbar.*

class ListComment : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_comment)

        val idPost = intent.getStringExtra("idPost").toInt()
        val postTitle = intent.getStringExtra("postTitle").toString()
        alterToolbar(postTitle)

        GetComments(this, recyclerviewListComments, idPost, circleBar).execute()
    }

    private fun alterToolbar(postTitle: String){
        val complementTitle = "O que a galera comentou sobre $postTitle"
        titleActionBar.text = resources.getString(R.string.postagem_recente)
        titleActionBar.setTextColor(Color.parseColor("#797979"))
        headerRecyclerComments.text = complementTitle
        setSupportActionBar(toolbar)
    }

    internal class GetComments internal constructor
    (activity: ListComment, recyclerView: RecyclerView, idPost: Int, progressBar: ProgressBar)
        : AsyncTask<Void, Void, Boolean>(), DetailsContract.ListCommentsView {

        private val mRecyclerview = recyclerView
        private val mActivity = activity
        private val id = idPost
        private val mProgressBar = progressBar
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
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)
            when (result) {
                true -> {
                    val mListPosts = mCommentAdapter.getListItems()
                    initRecyclerView()
                    mCommentAdapter.submitList(mListPosts)
                    mProgressBar.visibility = View.GONE
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

