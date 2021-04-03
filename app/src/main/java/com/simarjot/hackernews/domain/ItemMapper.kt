package com.simarjot.hackernews.domain

import com.simarjot.hackernews.data.Item

object ItemMapper {

    fun toStory(item: Item): Story {
        return Story(
            id = item.id,
            by = item.by,
            title = item.title ?: "No Title",
            kids = item.kids ?: listOf(),
            score = item.score ?: 0
        )
    }
}