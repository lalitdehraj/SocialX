package com.example.socialxtest.model

import java.text.SimpleDateFormat
import java.util.*


data class Article (
    var author: String ,
    var title: String ,
    var description: String,
    var urlToImage: String? ,
    var publishedAt: Date
){
    fun getFormattedDate() : String {
        val format = SimpleDateFormat("dd MMM,yy", Locale.getDefault())
        return format.format(Date())
    }
}

data class News(
    var articles: List<Article>
    )