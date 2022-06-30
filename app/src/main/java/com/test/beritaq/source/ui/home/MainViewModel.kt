package com.test.beritaq.source.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.beritaq.source.berita.BeritaModel
import com.test.beritaq.source.berita.BeritaRepository
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

    val berita by lazy {
        MutableLiveData<BeritaModel>()
    }

    init {
        detailKategori.value = ""
        pesan.value = ""
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            try {
                val response = repository.getData(
                    "",
                    "",
                    1
                )
                berita.value = response
            } catch (e: Exception) {
                pesan.value = "Terjadi Kesalahan"
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