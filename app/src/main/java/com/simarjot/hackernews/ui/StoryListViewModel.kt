package com.simarjot.hackernews.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simarjot.hackernews.domain.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryListViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    val isloading = MutableLiveData<Boolean>()

    fun loadIds() {
        viewModelScope.launch {
            isloading.value = true
            newsRepository.loadIds()
            isloading.value = false
        }
    }

    fun getPage() = newsRepository.getNewItems()
}