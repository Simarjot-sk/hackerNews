package com.simarjot.hackernews.domain

data class Story(
    val id: Int,
    val by: String,
    val title: String,
    val kids: List<Int>,
    val score:Int
)