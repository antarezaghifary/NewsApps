package com.lazday.news.ui.home

import androidx.lifecycle.ViewModel
import com.lazday.news.source.news.NewsRepository
import org.koin.dsl.module


val homeViewModel = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel(
        val repository: NewsRepository
) : ViewModel() {
    val title = "Berita"
}