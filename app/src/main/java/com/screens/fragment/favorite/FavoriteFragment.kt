package com.screens.fragment.favorite

//import com.di.DaggerFragmentComponent
import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.BaseFragment
import com.base.NevNewsApplication
import com.example.myapplication.R
import com.example.myapplication.databinding.BreakkingNewsFragmentBinding
import com.model.NewsResponse
import com.repository.FavoriteRepository
import com.screens.activity.MainActivityActivity
import com.screens.adapters.FavoriteNewsAdapter
import com.utlis.CustomActionBar
import com.utlis.NetworkResult
import com.utlis.callbackinterface.OnBackButtonClicked
import com.utlis.callbackinterface.setonItemClick
import com.utlis.hide
import com.utlis.show
import com.viewmodel.FavoriteViewModel
import com.viewmodel.viewmodelfactory.NewsViewModelFactory
import javax.inject.Inject

class FavoriteFragment :
    BaseFragment<BreakkingNewsFragmentBinding>(R.layout.breakking_news_fragment), setonItemClick {

    @Inject
    lateinit var customActionBar: CustomActionBar

    @Inject
    lateinit var favoriteNewsAdapter: FavoriteNewsAdapter

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var favoriteRepository: FavoriteRepository


    override fun init() {
        super.init()

        builderDaggerComponent()
        viewModel = ViewModelProvider(
            this,
            NewsViewModelFactory { FavoriteViewModel(favoriteRepository) }
        )[FavoriteViewModel::class.java]

        setRecyclerAdapters()
        setActionbar()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.breakingNews.observe(viewLifecycleOwner) {

            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                }

                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    favoriteNewsAdapter.submitList(it.data)

                }

                else -> {
                    binding.progressBar.hide()
                }
            }

        }
    }

    private fun setRecyclerAdapters() {

        binding.breakingNewsRecyclerView.adapter = favoriteNewsAdapter
        binding.breakingNewsRecyclerView.layoutManager = (LinearLayoutManager(requireContext()))
        favoriteNewsAdapter.setItemClickListener(this)

    }


    override fun builderDaggerComponent() {
        val component = (activity?.application as NevNewsApplication).applicationComponent
        component.getFragmentComponent().create(
            binding.actionbarView
        ).inject(this)
    }

    private fun setActionbar() {
        customActionBar.setActionBar(getString(R.string.Favorite))
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