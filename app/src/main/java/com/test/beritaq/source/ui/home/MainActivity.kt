package com.test.beritaq.source.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.beritaq.databinding.ActivityMainBinding
import com.test.beritaq.databinding.CustomToolbarBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module


val mainModule = module {
    factory {
        MainActivity()
    }
}

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var bindingToolbar: CustomToolbarBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindingToolbar = binding.toolbar

        bindingToolbar.tvTitle.text = viewModel.title
    }
}