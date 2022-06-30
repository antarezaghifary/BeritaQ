package com.test.beritaq.source.ui.home

import androidx.lifecycle.ViewModel
import com.test.beritaq.source.berita.BeritaRepository
import com.test.beritaq.source.berita.CategoryModel
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