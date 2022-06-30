package com.test.beritaq.source.ui.home

import androidx.lifecycle.ViewModel
import com.test.beritaq.source.berita.BeritaRepository
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
}