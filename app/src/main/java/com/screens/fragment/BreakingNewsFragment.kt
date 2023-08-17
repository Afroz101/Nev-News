package com.screens.fragment

//import com.di.DaggerFragmentComponent
import android.R.attr.value
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.BaseFragment
import com.base.NevNewsApplication
import com.db.respository.DataBaseImp
import com.example.myapplication.R
import com.example.myapplication.databinding.BreakkingNewsFragmentBinding
import com.model.NewsResponse
import com.repository.NewsRepository
import com.screens.activity.MainActivityActivity
import com.screens.adapters.BreakingNewsAdapter
import com.screens.adapters.LoaderAdapter
import com.utlis.CustomActionBar
import com.utlis.callbackinterface.OnBackButtonClicked
import com.utlis.callbackinterface.setonItemClick
import com.utlis.hide
import com.utlis.show
import com.viewmodel.NewsViewModel
import com.viewmodel.viewmodelfactory.NewsViewModelFactory
import javax.inject.Inject


class BreakingNewsFragment :
    BaseFragment<BreakkingNewsFragmentBinding>(R.layout.breakking_news_fragment),
    setonItemClick {


    @Inject
    lateinit var dataBaseImp: DataBaseImp

    @Inject
    lateinit var breakingNewsAdapter: BreakingNewsAdapter

    lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var newsRepository: NewsRepository

    @Inject
    lateinit var loaderAdapter: LoaderAdapter

    @Inject
    lateinit var customActionBar: CustomActionBar
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        println("")
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
            header = loaderAdapter, footer = loaderAdapter
        )
        binding.breakingNewsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
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
        component.getFragmentComponent().create(binding.actionbarView)
            .inject(this)

//        DaggerFragmentComponent.factory().create(requireContext(), binding.actionbarView,component)
//            .inject(this)


    }

    private fun setActionbar() {
        customActionBar.apply {
            hideBackButton()
            setActionBar(getString(R.string.Home))
            showFavListIcon()
            setActionbarBackPress(object : OnBackButtonClicked {
                override fun backButtonClicked() {
                }

                override fun otherActionbarIconClick(view: View) {
                    findNavController().navigate(R.id.favoriteFragment)
                }
            })
        }
    }

    override fun onNewsItemClicked(article: NewsResponse.Article, context: Context) {

        callNewsDetailFragment(article)


    }
}