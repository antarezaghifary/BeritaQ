package com.test.beritaq.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.test.beritaq.databinding.ActivityMainBinding
import com.test.beritaq.databinding.CustomToolbarBinding
import com.test.beritaq.source.berita.ArticlesItem
import com.test.beritaq.source.berita.CategoryModel
import com.test.beritaq.ui.berita.BeritaAdapter
import com.test.beritaq.ui.berita.CategoryAdapter
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
                viewModel.detailKategori.postValue(category.id)
            }
        })
    }

    private val beritaAdapter by lazy {
        BeritaAdapter(arrayListOf(), object : BeritaAdapter.OnAdapterListener {
            @SuppressLint("LogNotTimber")
            override fun onClick(articleModel: ArticlesItem) {

            }
        })
    }

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindingToolbar = binding.toolbar

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        bindingToolbar.tvTitle.text = viewModel.title
        binding.listCategory.adapter = categoryAdapter

        viewModel.detailKategori.observe(this) {
            Log.e("TAG", "detail kategori: ${it}")
            viewModel.getData()
        }

        binding.listBerita.adapter = beritaAdapter

        viewModel.berita.observe(this) {
            Log.e("TAG", "data berita: ${it.articles}")
            binding.imageAlert.visibility = if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            binding.textAlert.visibility = if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            beritaAdapter.addData(it.articles)
        }

        viewModel.pesan.observe(this) {
            it?.let {
                Log.e("TAG", "Pesan: ${it}")
            }
        }
    }
}