package com.simarjot.hackernews.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Item(
    @Json(name = "id")
    val id: Int,
    val type: String,
    val by: String,
    val kids: List<Int>? = null,
    val title: String? = null,
    val text: String? = null,
    val score: Int? = null
)