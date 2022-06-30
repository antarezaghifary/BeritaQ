package com.test.beritaq.source.network

import com.test.beritaq.source.BeritaModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("")
    suspend fun getBerita(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("q") q: String,
    ): BeritaModel
}