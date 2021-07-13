package com.lazday.news.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lazday.news.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    //Pertama buat binding ActivityDetailBinding dari activity_detail
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Kedua Ubah activity_binding ke binding.root
        setContentView(binding.root)

        //Ketiga


    }
}