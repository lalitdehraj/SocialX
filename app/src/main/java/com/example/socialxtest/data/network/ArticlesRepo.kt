package com.example.socialxtest.data.network

import android.content.Context
import android.net.Uri
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.socialxtest.model.Article
import com.example.socialxtest.model.News
import com.example.socialxtest.utils.URL
import com.google.gson.Gson

data class ArticlesRepo (val context: Context){

    companion object{
        fun getInstance(context: Context): ArticlesRepo {
            return ArticlesRepo(context)
        }
    }


    private val queue = Volley.newRequestQueue(context)

    fun fetchNewsArticles(
        onSuccess: (newsItems: List<Article>) -> Unit,
        onFailure: (NetworkResponse) -> Unit
    ) {
        val newsArticlesRequest = object : StringRequest(
            Request.Method.GET,
            Uri.parse(URL).toString(),
            {
                val news: News = Gson().fromJson(it, News::class.java)
                onSuccess(news.articles)
            },
            {
                onFailure(it.networkResponse)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Api-Key"] = "5df7ffce89c64305b7843aab14523344"
                headers["Content-Type"] = "application/form-data"
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        queue.add(newsArticlesRequest)
    }
}