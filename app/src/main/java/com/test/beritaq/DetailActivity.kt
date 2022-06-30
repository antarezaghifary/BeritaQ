package com.test.beritaq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.beritaq.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}