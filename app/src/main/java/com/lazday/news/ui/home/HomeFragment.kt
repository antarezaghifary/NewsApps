package com.lazday.news.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lazday.news.databinding.CustomToolbarBinding
import com.lazday.news.databinding.FragmentHomeBinding
import com.lazday.news.source.news.ArticleModel
import com.lazday.news.source.news.CategoryModel
import com.lazday.news.ui.detail.DetailActivity
import com.lazday.news.ui.news.CategoryAdapter
import com.lazday.news.ui.news.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import timber.log.Timber

val homeModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        //bindingToolbar.textTitle.text = viewModel.title
        bindingToolbar.title = viewModel.title
        //Timber.e(viewModel.categories.toString())
        binding.listCategory.adapter = categoryAdapter

        viewModel.category.observe(viewLifecycleOwner, {
            Timber.e(it)
            viewModel.fetch()
        })

        binding.listNews.adapter = newsAdapter

        viewModel.news.observe(viewLifecycleOwner, {
            Timber.e(it.articles.toString())
            //binding.imageAlert.visibility = if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            //binding.textAlert.visibility = if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            newsAdapter.addData(it.articles)
        })

        viewModel.message.observe(viewLifecycleOwner, {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private val newsAdapter by lazy {
        NewsAdapter(arrayListOf(), object : NewsAdapter.OnAdapterListener {
            override fun onClick(articleModel: ArticleModel) {
                startActivity(
                        Intent(requireActivity(), DetailActivity::class.java)
                                .putExtra("intent_detail", articleModel))

            }
        })
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.categories, object : CategoryAdapter.OnAdapterListener {
            override fun onClick(category: CategoryModel) {
                viewModel.category.postValue(category.id)
            }
        })
    }
}