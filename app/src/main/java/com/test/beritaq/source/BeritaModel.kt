package com.test.beritaq.source

data class BeritaModel(
    val status: String,
    val totalResults: Int,
    val article: List<ArticleModel>
)

data class ArticleModel(
    val source: SourceModel?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImg: String?,
    val publishedAt: String,
    val content: String?
)

data class SourceModel(
    val id: String?,
    val name: String?,
)