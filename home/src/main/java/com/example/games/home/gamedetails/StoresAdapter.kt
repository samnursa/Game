package com.example.games.home.gamedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.games.core.domain.model.Game
import com.example.games.home.R
import com.example.games.home.databinding.ItemStoreBinding

class StoresAdapter(
    private val screenshots: List<Game.Stores>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<StoresAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false))

    override fun getItemCount() = screenshots.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = screenshots[position]
        holder.bind(data, listener)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemStoreBinding.bind(itemView)
        fun bind(store: Game.Stores, listener: (String) -> Unit) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(store.store.slug?.let { showIcon(it) })
                    .transform(CenterCrop())
                    .into(img)
                itemView.setOnClickListener {
                    store.store.domain?.let { domain -> listener(domain) }
                }
            }
        }
    }

    private fun showIcon(slug: String): Int{
        return when(slug){
            "playstation-store" -> R.drawable.ic_playstation
            "epic-games" -> R.drawable.ic_epic
            "steam" -> R.drawable.ic_steam
            "xbox360", "xbox-store" -> R.drawable.ic_xbox
            "gog" -> R.drawable.ic_gog
            "nintendo" -> R.drawable.ic_nintendo
            "google-play" -> R.drawable.ic_google_play
            "apple-appstore" -> R.drawable.ic_app_store
            else -> R.drawable.ic_image_not_supported
        }
    }
}