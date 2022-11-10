package com.example.socialxtest.ui.newsfeed

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socialxtest.R
import com.example.socialxtest.data.network.ArticlesRepo
import com.example.socialxtest.databinding.FragmentNewsFeedBinding
import com.example.socialxtest.ui.NewsFeed.NewsAdapter
import com.example.socialxtest.ui.NewsFeed.NewsViewModel


class NewsFeed : Fragment(R.layout.fragment_news_feed) {

    private lateinit var binding : FragmentNewsFeedBinding
    private lateinit var viewModel: NewsViewModel

    private val newsAdapter by lazy {
        NewsAdapter(
            inflater = layoutInflater
        )
    }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsFeedBinding.bind(view)
        binding.lifecycleOwner = this
        binding.emptyListMsg.visibility = View.GONE


        viewModel = ViewModelProvider(this,
            NewsViewModel.Factory(requireActivity().application,ArticlesRepo.getInstance(requireContext())))[NewsViewModel::class.java]

        binding.articlesList.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.getArticles(
            onSuccess = {
                newsAdapter.submitList(it) },

            onFailure = {
                binding.emptyListMsg.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "${it.statusCode} : ${it.data}", Toast.LENGTH_LONG).show()
            }
        )
    }



}