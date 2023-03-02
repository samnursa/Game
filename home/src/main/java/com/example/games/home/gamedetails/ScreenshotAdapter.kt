package com.example.games.home.gamedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.games.core.domain.model.Game
import com.example.games.home.R
import com.example.games.home.databinding.ItemScreenshotBinding

class ScreenshotAdapter(
    private val screenshots: List<Game.Screenshots>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<ScreenshotAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_screenshot, parent, false))

    override fun getItemCount() = screenshots.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = screenshots[position]
        holder.bind(data, listener)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemScreenshotBinding.bind(itemView)
        fun bind(screenshot: Game.Screenshots, listener: (String) -> Unit) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(screenshot.image)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(img)
                itemView.setOnClickListener {
                    listener(screenshot.image)
                }
            }
        }
    }
}