package com.test.beritaq.source.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.beritaq.databinding.ActivityMainBinding
import com.test.beritaq.databinding.CustomToolbarBinding
import com.test.beritaq.source.berita.CategoryModel
import com.test.beritaq.source.ui.berita.CategoryAdapter
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

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.kategories, object : CategoryAdapter.OnAdapterListener {
            @SuppressLint("LogNotTimber")
            override fun onClick(category: CategoryModel) {
                Log.e("TAG", "onClick: ${category.id}")
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindingToolbar = binding.toolbar

        bindingToolbar.tvTitle.text = viewModel.title
        binding.listCategory.adapter = categoryAdapter
    }
}