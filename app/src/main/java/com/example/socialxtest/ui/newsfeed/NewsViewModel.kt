package com.example.socialxtest.ui.NewsFeed

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.socialxtest.data.network.ArticlesRepo
import com.example.socialxtest.model.Article
import com.example.socialxtest.model.News
import com.google.gson.Gson

class NewsViewModel(
    private val articlesRepo: ArticlesRepo,
    private val context : Application
) : AndroidViewModel(context) {

    fun getArticles(onSuccess: (List<Article>) -> Unit, onFailure: (NetworkResponse) -> Unit) {
        articlesRepo.fetchNewsArticles(onSuccess, onFailure)
    }


@Suppress("UNCHECKED_CAST")
class Factory(
        private val application: Application,
        private val articlesRepo: ArticlesRepo
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewsViewModel(articlesRepo,application) as T
        }
    }
}