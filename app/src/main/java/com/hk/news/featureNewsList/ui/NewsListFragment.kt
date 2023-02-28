package com.hk.news.featureNewsList.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hk.news.R
import com.hk.news.core.ui.collectOn
import com.hk.news.core.ui.viewBinding
import com.hk.news.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment(R.layout.fragment_news_list) {

    private val binding by viewBinding(FragmentNewsListBinding::bind)
    private val viewModel by viewModels<NewsListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // region initView
        binding.retry.btnRetry.setOnClickListener {
            viewModel.updateTopHeadLines()
        }
        // endregion initView

        // region initRecyclerView
        val adapter = NewsListAdapter { news ->

        }
        binding.newsList.let { newsList ->
            newsList.adapter = adapter
            newsList.setHasFixedSize(true)
        }
        // endregion initRecyclerView

        // region initObservation
        viewModel.uiState.collectOn(viewLifecycleOwner) { uiState ->

            binding.apply {
                progress.isVisible = uiState.isLoading
                newsList.isVisible = !uiState.isLoading
                retry.root.isVisible = !uiState.isLoading && uiState.error != null
                uiState.error?.let { retry.errorMessage.text = getText(it.messageId) }
            }

            adapter.submitList(uiState.newsList)
        }
        // endregion initObservation
    }
}