package com.simarjot.hackernews.domain

import android.util.Log
import com.simarjot.hackernews.data.NewsService
import kotlinx.coroutines.flow.flow

class NewsRepository(private val newsService: NewsService) {
    private val TAG = "story"
    companion object{
        private const val pageSize = 10
    }
    private var itemsLoadedTill = 0
    private var ids: List<Int>? = null

    suspend fun loadIds() {
        Log.d(TAG, "loadIds: loading ids")
        ids = newsService.getTopStories()
        Log.d(TAG, "loadIds: ids loaded")
    }

    fun getNewItems() = flow<List<Story>> {
        if (ids == null) emit(listOf())
        val stories = mutableListOf<Story>()

        var index = itemsLoadedTill
        for (i in 1..pageSize){
            val id = ids!![index++]
            val item = newsService.getItem(id)
            val story = ItemMapper.toStory(item)
            stories.add(story)
        }
        itemsLoadedTill = index
        emit(stories)
    }
}
