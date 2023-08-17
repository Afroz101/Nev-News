package com.screens.fragment

//import com.di.DaggerFragmentComponent
import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.BaseFragment
import com.base.NevNewsApplication
import com.example.myapplication.R
import com.example.myapplication.databinding.BreakkingNewsFragmentBinding
import com.model.NewsResponse
import com.repository.CricketRepository
import com.screens.activity.MainActivityActivity
import com.screens.adapters.BreakingNewsAdapter
import com.screens.adapters.LoaderAdapter
import com.utlis.CustomActionBar
import com.utlis.callbackinterface.OnBackButtonClicked
import com.utlis.callbackinterface.setonItemClick
import com.utlis.hide
import com.utlis.show
import com.viewmodel.CricketViewModel
import com.viewmodel.viewmodelfactory.NewsViewModelFactory
import javax.inject.Inject

class CricketFragment :
    BaseFragment<BreakkingNewsFragmentBinding>(R.layout.breakking_news_fragment), setonItemClick {

    @Inject
    lateinit var customActionBar: CustomActionBar

    @Inject
    lateinit var breakingNewsAdapter: BreakingNewsAdapter

    private lateinit var viewModel: CricketViewModel

    @Inject
    lateinit var cricketRepository: CricketRepository


    override fun init() {
        super.init()

        builderDaggerComponent()
        viewModel = ViewModelProvider(
            this,
            NewsViewModelFactory { CricketViewModel(cricketRepository) }
        )[CricketViewModel::class.java]

        setRecyclerAdapters()
        pageLoadListener()
        setActionbar()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.list.observe(viewLifecycleOwner) {
            breakingNewsAdapter.submitData(lifecycle, it)
        }
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
        customActionBar.setActionBar(getString(R.string.Cricket))
        customActionBar.showBackButton()
        customActionBar.setActionbarBackPress(object : OnBackButtonClicked {
            override fun backButtonClicked() {
                (activity as MainActivityActivity).onBackPressed()
            }

            override fun otherActionbarIconClick(view: View) {
            }
        })
    }

    override fun onNewsItemClicked(article: NewsResponse.Article, context: Context) {
        callNewsDetailFragment(article)

    }


}