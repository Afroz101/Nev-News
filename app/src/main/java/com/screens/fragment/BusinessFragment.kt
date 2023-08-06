package com.screens.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.BaseFragment
import com.base.NevNewsApplication
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBreakkingNewsFragmentBinding
import com.repository.BusinessRepository
import com.repository.NewsRepository
import com.screens.adapters.BreakingNewsAdapter
import com.screens.adapters.LoaderAdapter
import com.utlis.callbackinterface.ResponseCallBack
import com.utlis.hide
import com.utlis.show
import com.viewmodel.BusinessViewModel
import com.viewmodel.NewsViewModel
import com.viewmodel.viewmodelfactory.NewsViewModelFactory
import javax.inject.Inject

class BusinessFragment :
    BaseFragment<FragmentBreakkingNewsFragmentBinding>(R.layout.fragment_breakking_news_fragment){

    @Inject
     lateinit var breakingNewsAdapter: BreakingNewsAdapter
     private lateinit var viewModel: BusinessViewModel

     @Inject
     lateinit var businessRepository: BusinessRepository

    override fun init() {
        super.init()

        (activity?.application as NevNewsApplication).applicationComponent.inject(this)
        viewModel = ViewModelProvider(
            this,
            NewsViewModelFactory{ BusinessViewModel(businessRepository) }
        )[BusinessViewModel::class.java]
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
            footer = LoaderAdapter()
        )
        binding.breakingNewsRecyclerView.layoutManager = (LinearLayoutManager(requireContext()))
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