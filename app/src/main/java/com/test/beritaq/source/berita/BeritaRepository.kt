package com.test.beritaq.source.berita

import com.test.beritaq.BuildConfig
import com.test.beritaq.source.network.MyApi
import org.koin.dsl.module


val repositoryModule = module {
    factory {
        BeritaRepository(get())
    }
}

class BeritaRepository(
    private val api: MyApi
) {
    suspend fun getData(
        category: String,
        query: String,
        page: Int
    ): BeritaModel {
        return api.getBerita(
            BuildConfig.API_KEY,
            "id",
            category,
            page,
            query
        )
    }
}
