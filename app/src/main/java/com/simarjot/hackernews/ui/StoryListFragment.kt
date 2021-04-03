package com.simarjot.hackernews.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.simarjot.hackernews.R
import com.simarjot.hackernews.domain.Story
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoryListFragment : Fragment(R.layout.fragment_story_list) {
    private val viewModel by viewModels<StoryListViewModel>()
    private val storyList = mutableListOf<Story>()
    private val storyAdapter = StoryAdapter(storyList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadIds()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)

        recyclerView.adapter = storyAdapter

        viewModel.isloading.observe(viewLifecycleOwner, Observer { isLoading ->
            progressBar.isVisible = isLoading
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1)){
                    loadNewData()
                    viewModel.isloading.value = true
                }
            }
        })
    }

    private fun loadNewData() {
        lifecycleScope.launch {
            viewModel.getPage().collect { page ->
                storyList.addAll(page)
                storyAdapter.notifyDataSetChanged()
                viewModel.isloading.value = false
            }
        }
    }
}