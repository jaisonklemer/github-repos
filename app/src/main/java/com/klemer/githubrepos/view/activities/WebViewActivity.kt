package com.klemer.githubrepos.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.klemer.githubrepos.databinding.ActivityWebViewBinding

class WebViewActivity : BaseActivity() {

    private lateinit var binding: ActivityWebViewBinding
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val url = intent.getStringExtra("url").toString()
        val pageTitle = intent.getStringExtra("title").toString()

        setupWebView()
        setupProgressBar()
        setupActionBar(pageTitle)
        loadUrl(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView = binding.webView
        webView.settings.javaScriptEnabled = true
    }

    private fun setupProgressBar() {
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                binding.progressBar.progress = progress
            }
        }
    }

    private fun loadUrl(url: String) {
        webView.loadUrl(url)
    }

    private fun setupActionBar(pageTitle: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = pageTitle
    }


}