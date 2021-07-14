package com.lazday.news.source.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lazday.news.source.news.ArticleModel
import com.lazday.news.source.news.NewsDao

@Database(
        entities = [ArticleModel::class],
        version = 1,
        exportSchema = false
)
abstract class DatabaseClient : RoomDatabase() {
    abstract val newsDao: NewsDao
}