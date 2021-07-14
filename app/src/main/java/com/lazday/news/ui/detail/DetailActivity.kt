package com.lazday.news.ui.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.lazday.news.databinding.ActivityDetailBinding
import com.lazday.news.databinding.CustomToolbarBinding
import com.lazday.news.source.news.ArticleModel

class DetailActivity : AppCompatActivity() {

    //Pertama buat binding ActivityDetailBinding dari activity_detail
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val detail by lazy {
        intent.getSerializableExtra("intent_detail") as ArticleModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Kedua Ubah activity_binding ke binding.root
        setContentView(binding.root)

        bindingToolbar = binding.toolbar

        setSupportActionBar(bindingToolbar.container)
        supportActionBar!!.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true) //
        }

        detail.let {
            val web = binding.webView
            web.loadUrl(it.url!!)
            web.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    //selesai load maka
                    binding.progressTop.visibility = View.GONE
                }
            }
            val settings = binding.webView.settings
            settings.javaScriptCanOpenWindowsAutomatically = false
        }

        //Ketiga


    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}