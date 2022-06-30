package com.test.beritaq.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.beritaq.source.berita.BeritaRepository
import com.test.beritaq.source.berita.BeritasResponse
import com.test.beritaq.source.berita.CategoryModel
import kotlinx.coroutines.launch
import org.koin.dsl.module

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

    val berita by lazy {
        MutableLiveData<BeritasResponse>()
    }

    init {
        detailKategori.value = ""
        pesan.value = ""
    }

    fun getData() {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getData(
                    detailKategori.value!!,
                    1,
                    ""
                )
                berita.value = response
                loading.value = false
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