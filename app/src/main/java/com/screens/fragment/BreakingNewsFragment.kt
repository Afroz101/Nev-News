package com.screens.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.BaseFragment
import com.di.ActivityModule
import com.di.CustomActionBarModule
import com.di.DaggerApplicationComponent
import com.example.myapplication.R
import com.example.myapplication.databinding.BreakkingNewsFragmentBinding
import com.model.NewsResponse
import com.repository.NewsRepository
import com.screens.activity.NewsDetailActivity
import com.screens.activity.Test
import com.screens.adapters.BreakingNewsAdapter
import com.screens.adapters.LoaderAdapter
import com.utlis.CustomActionBar
import com.utlis.callbackinterface.setonItemClick
import com.utlis.hide
import com.utlis.show
import com.viewmodel.NewsViewModel
import com.viewmodel.viewmodelfactory.NewsViewModelFactory
import retrofit2.Retrofit
import javax.inject.Inject

class BreakingNewsFragment :
    BaseFragment<BreakkingNewsFragmentBinding>(R.layout.breakking_news_fragment), setonItemClick {
//    private val mContext: Context? = null

    @Inject
    lateinit var breakingNewsAdapter: BreakingNewsAdapter

    lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var newsRepository: NewsRepository

    @Inject
    lateinit var newsRepository2: NewsRepository

    @Inject
    lateinit var customActionBar: CustomActionBar
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun init() {
        super.init()

        builderDaggerComponent()

        viewModel = ViewModelProvider(
            this,
            NewsViewModelFactory { NewsViewModel(newsRepository) })[NewsViewModel::class.java]


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
            header = LoaderAdapter(), footer = LoaderAdapter()
        )
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

    private fun builderDaggerComponent() {
        DaggerApplicationComponent.factory().create(requireContext(), binding.actionbarView)
            .inject(this)
    }

    private fun setActionbar() {
        customActionBar.setActionBar(getString(R.string.Home))
        customActionBar.hideBackButton()
    }

    override fun onNewsItemClicked(article: NewsResponse.Article) {
//        val args = Bundle()
//        args.putParcelable("USER", article)
//        Navigation.findNavController(binding.root.rootView).navigate(R.id.test)

//        val navController = findNavController()
//        navController.navigate(R.id.action_fragment_to_target_activity)
//        val intent = Intent (this@BreakingNewsFragment.context, NewsDetailActivity::class.java)
//        startActivity(intent)

//        requireActivity().run{
        val args = Bundle()
        args.putParcelable("USER", article)
        Navigation.findNavController(binding.root).navigate(R.id.newsdetails_fragment, args)
//        }


    }
}