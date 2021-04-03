package com.simarjot.hackernews.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simarjot.hackernews.R
import com.simarjot.hackernews.domain.Story

class StoryAdapter(private val list: List<Story>) : RecyclerView.Adapter<StoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_story, parent, false)
        return StoryViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = list[position]
        holder.title.text = story.title
        holder.author.text = story.by
        holder.score.text = story.score.toString()
    }
}


class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.title)
    val author: TextView = view.findViewById(R.id.author)
    val score: TextView = view.findViewById(R.id.score)
}