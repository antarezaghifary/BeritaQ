package com.test.beritaq.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.test.beritaq.R
import com.test.beritaq.databinding.ActivityMainBinding
import com.test.beritaq.databinding.CustomToolbarBinding
import com.test.beritaq.source.berita.ArticlesItem
import com.test.beritaq.source.berita.CategoryModel
import com.test.beritaq.ui.berita.BeritaAdapter
import com.test.beritaq.ui.berita.CategoryAdapter
import com.test.beritaq.ui.detail.DetailActivity
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

    private fun firstLoad() {
        binding.scroll.scrollTo(0, 0)
        viewModel.getData()
    }

    private val beritaAdapter by lazy {
        BeritaAdapter(arrayListOf(), object : BeritaAdapter.OnAdapterListener {
            @SuppressLint("LogNotTimber")
            override fun onClick(articleModel: ArticlesItem) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("intent_data", articleModel.url)
                startActivity(intent)
            }
        })
    }

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindingToolbar = binding.toolbar
        bindingToolbar.containerBar.inflateMenu(R.menu.search_menu)
        val menu = binding.toolbar.containerBar.menu
        val search = menu.findItem(R.id.searchView)
        val searchView = search.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                firstLoad()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                p0?.let { viewModel.query = it }
                return true
            }

        })

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        bindingToolbar.tvTitle.text = viewModel.title
        binding.listCategory.adapter = categoryAdapter

        viewModel.detailKategori.observe(this) {
            Log.e("TAG", "detail kategori: ${it}")
            firstLoad()
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