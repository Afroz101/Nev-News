package com.screens.fragment

import android.app.Application
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.BaseFragment
import com.base.NevNewsApplication
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBreakkingNewsFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.screens.adapters.BreakingNewsAdapter
import com.screens.adapters.LoaderAdapter
import com.network.ApiClient
import com.repository.NewsRepository
import com.utlis.callbackinterface.ResponseCallBack
import com.utlis.hide
import com.utlis.show
import com.viewmodel.NewsViewModel
import com.viewmodel.viewmodelfactory.NewsViewModelFactory
import javax.inject.Inject

class BreakingNewsFragment :
    BaseFragment<FragmentBreakkingNewsFragmentBinding>(R.layout.fragment_breakking_news_fragment){

    @Inject
    lateinit var breakingNewsAdapter: BreakingNewsAdapter

    lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var newsRepository: NewsRepository


    override fun init() {
        super.init()
        (activity?.application  as NevNewsApplication).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this,
            NewsViewModelFactory{ NewsViewModel(newsRepository) }
        )[NewsViewModel::class.java]
        setRecyclerAdapters()
        pageLoadListener()

    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.list.observe(viewLifecycleOwner, Observer {
            breakingNewsAdapter.submitData(lifecycle, it)
        })
    }

    private fun setRecyclerAdapters() {
        binding.breakingNewsRecyclerView.adapter = breakingNewsAdapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter())
        binding.breakingNewsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun pageLoadListener() {

        breakingNewsAdapter.addLoadStateListener { loadState ->
            when (val state = loadState.source.refresh) {
                is LoadState.NotLoading -> {
                    binding.progressBar.hide()
                }
                is LoadState.Loading -> {
                   binding.progressBar.show()
                }
                is LoadState.Error -> {
                    binding.progressBar.hide()
                    showSnackbarMessage(state.error.message.orEmpty())
                }
            }
        }
    }

}