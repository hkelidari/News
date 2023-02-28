package com.hk.news.featureNewsDetails.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.hk.news.R
import com.hk.news.core.ui.collectOn
import com.hk.news.core.ui.viewBinding
import com.hk.news.databinding.FragmentNewsDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {

    private val binding by viewBinding(FragmentNewsDetailsBinding::bind)
    private val viewModel by viewModels<NewsDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.collectOn(viewLifecycleOwner) { uiState ->
            binding.apply {
                uiState.newsDetails?.let {
                    newsTitle.text = it.title
                    newsDescription.text = it.description
                    newsContent.text = it.content
                    newsImage.load(it.urlToImage)
                }

            }
        }
    }
}