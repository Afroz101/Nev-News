package com.screens.fragment

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.BaseFragment
import com.base.NevNewsApplication
//import com.di.DaggerFragmentComponent
import com.example.myapplication.R
import com.example.myapplication.databinding.BreakkingNewsFragmentBinding
import com.model.NewsResponse
import com.repository.BusinessRepository
import com.screens.activity.MainActivityActivity
import com.screens.adapters.BreakingNewsAdapter
import com.screens.adapters.LoaderAdapter
import com.utlis.CustomActionBar
import com.utlis.callbackinterface.OnBackButtonClicked
import com.utlis.callbackinterface.setonItemClick
import com.utlis.hide
import com.utlis.show
import com.viewmodel.BusinessViewModel
import com.viewmodel.viewmodelfactory.NewsViewModelFactory
import javax.inject.Inject

class BusinessFragment :
    BaseFragment<BreakkingNewsFragmentBinding>(R.layout.breakking_news_fragment), setonItemClick {

    @Inject
    lateinit var customActionBar: CustomActionBar

    @Inject
    lateinit var breakingNewsAdapter: BreakingNewsAdapter

    private lateinit var viewModel: BusinessViewModel

    @Inject
    lateinit var businessRepository: BusinessRepository


    override fun init() {
        super.init()

        builderDaggerComponent()
        viewModel = ViewModelProvider(
            this,
            NewsViewModelFactory { BusinessViewModel(businessRepository) }
        )[BusinessViewModel::class.java]

        setRecyclerAdapters()
        pageLoadListener()
        setActionbar()
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
        breakingNewsAdapter.setItemClickListener(this)

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


    override fun builderDaggerComponent() {
        val component = (activity?.application as NevNewsApplication).applicationComponent
        component.getFragmentComponent().create(
            binding.actionbarView
        ).inject(this)
//        DaggerFragmentComponent.factory().create(requireContext(), binding.actionbarView,component)
//            .inject(this)


    }

    private fun setActionbar() {
        customActionBar.setActionBar(getString(R.string.Business))
        customActionBar.showBackButton()
        customActionBar.setActionbarBackPress(object : OnBackButtonClicked {
            override fun backButtonClicked() {
                (activity as MainActivityActivity).onBackPressed()
            }

            override fun otherActionbarIconClick(view: View) {
            }
        });
    }

    override fun onNewsItemClicked(article: NewsResponse.Article, context: Context) {
        callNewsDetailFragment(article)


    }


}