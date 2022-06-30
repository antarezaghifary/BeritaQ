package com.test.beritaq.ui.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.test.beritaq.databinding.ActivityDetailBinding
import com.test.beritaq.databinding.CustomToolbarBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var bindingToolbar: CustomToolbarBinding

    private val detail by lazy {
        intent.getStringExtra("intent_data")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindingToolbar = binding.toolbar

        setSupportActionBar(bindingToolbar.containerBar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }

        val web = binding.webView
        web.loadUrl(detail!!)
        web.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressTop.visibility = View.GONE
            }
        }
        val setting = binding.webView.settings
        setting.javaScriptCanOpenWindowsAutomatically = false
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}