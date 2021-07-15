package com.lazday.news.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazday.news.source.news.CategoryModel
import com.lazday.news.source.news.NewsModel
import com.lazday.news.source.news.NewsRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module
import kotlin.math.ceil

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel(
        val repository: NewsRepository
) : ViewModel() {
    val title = "Berita"
    val category by lazy { MutableLiveData<String>() }
    val message by lazy { MutableLiveData<String>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val news by lazy { MutableLiveData<NewsModel>() }
    val loadMore by lazy { MutableLiveData<Boolean>() }

    init {
        //todo dijalankan ketika view model dipanggil
        category.value = ""
        message.value = null
        //fetch()
    }

    var query = ""

    //buat pagination
    var page = 1
    var totalMax = 1

    fun fetch() {
        loading.value = true
        if (page > 1) loadMore.value = true else loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.fetchApi(category.value!!, query, page)
                news.value = response
                //
                val totalResult: Double = response.totalResults / 20.0
                totalMax = ceil(totalResult).toInt()
                page++
                loadMore.value = false

                loading.value = false
            } catch (e: Exception) {
                message.value = "Terjadi kesalahan"
            }
        }
    }

    val categories = listOf<CategoryModel>(
            CategoryModel("", "Berita Utama"),
            CategoryModel("business", "Bisnis"),
            CategoryModel("entertainment", "Hiburan"),
            CategoryModel("general", "Umum"),
            CategoryModel("health", "Kesehatan"),
            CategoryModel("science", "Sains"),
            CategoryModel("esports", "Olah Raga"),
            CategoryModel("technology", "Teknologi"),

            )
}