package br.com.rogalabs.postsapi.util

import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar

class AsycCircular(var progressBar: ProgressBar) : AsyncTask<Void, Int, Boolean>() {

    override fun onPostExecute(result: Boolean) {
        progressBar.visibility = View.GONE
    }

    override fun onPreExecute() {
        progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg params: Void): Boolean {
        try {

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return true
    }

    override fun onProgressUpdate(vararg values: Int?) {
        values[0]?.let { progressBar.progress = it }
        values[0]?.let { progressBar.secondaryProgress = it + 15 }
    }
}