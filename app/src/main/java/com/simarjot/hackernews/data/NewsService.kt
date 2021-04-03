package com.simarjot.hackernews.data

import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("topstories.json")
    suspend fun getTopStories(): List<Int>

    @GET("item/{id}.json")
    suspend fun getItem(@Path("id") id: Int) : Item
}
