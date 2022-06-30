package com.test.beritaq.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.beritaq.source.berita.BeritaRepository
import com.test.beritaq.source.berita.BeritasResponse
import com.test.beritaq.source.berita.CategoryModel
import kotlinx.coroutines.launch
import org.koin.dsl.module
import kotlin.math.ceil

val mainViewModel = module {
    factory {
        MainViewModel(get())
    }
}

class MainViewModel(
    val repository: BeritaRepository
) : ViewModel() {

    val title = "Berita"

    val detailKategori by lazy {
        MutableLiveData<String>()
    }

    val pesan by lazy {
        MutableLiveData<String>()
    }

    val loading by lazy {
        MutableLiveData<Boolean>()
    }

    val loadMore by lazy {
        MutableLiveData<Boolean>()
    }

    val berita by lazy {
        MutableLiveData<BeritasResponse>()
    }

    init {
        detailKategori.value = ""
        pesan.value = ""
    }

    var query = ""
    var page = 1
    var total = 1

    fun getData() {

        if (page > 1) {
            loadMore.value = true
        } else {
            loading.value = true
        }

        viewModelScope.launch {
            try {
                val response = repository.getData(
                    detailKategori.value!!,
                    page,
                    query
                )
                berita.value = response
                val totalResult: Double = response.totalResults!! / 20.0
                total = ceil(totalResult).toInt()
                page++
                loading.value = false
                loadMore.value = false
            } catch (e: Exception) {
                pesan.value = e.message.toString()
            }
        }
    }


    val kategories = listOf<CategoryModel>(
        CategoryModel("", "Berita Utama"),
        CategoryModel("business", "Bisnis"),
        CategoryModel("entertainment", "Hiburan"),
        CategoryModel("general", "Umum"),
        CategoryModel("health", "Kesehatan"),
        CategoryModel("science", "Sains"),
        CategoryModel("sport", "Olah Raga"),
        CategoryModel("technology", "Teknologi")
    )
}