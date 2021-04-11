package com.lazday.news.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazday.news.retrofit.NewsModel
import com.lazday.news.retrofit.NewsRepository
import com.lazday.news.room.BookmarkModel
import kotlinx.coroutines.launch
import org.koin.dsl.module

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    val news by lazy { MutableLiveData<NewsModel>() }

    init {
        viewModelScope.launch {
            news.value =  repository.topHeadlines()
        }
    }

    fun add (article: NewsModel.Article) {
        viewModelScope.launch {
            repository.add( article = article )
        }
    }

}