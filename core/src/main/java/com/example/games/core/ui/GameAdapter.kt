package com.example.games.core.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.games.core.R
import com.example.games.core.databinding.ItemGamesBinding
import com.example.games.core.domain.model.Game
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class GameAdapter(
    private val listener: (Game) -> Unit
) : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {

    private var listData = ArrayList<Game>()

    fun setData(newListData: List<Game>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_games, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data, listener)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGamesBinding.bind(itemView)
        fun bind(game: Game, listener: (Game) -> Unit) {
            with(binding) {
                chipGenre.removeAllViews()
                txtTitleList.text = game.name
                rate.text = game.rating.toString()
                txtDate.text = game.released
                Glide.with(itemView.context)
                    .load(game.background_image)
                    .transform(CenterCrop(), RoundedCorners(16))
                    .into(imgPoster)

                game.genres.forEachIndexed { index, genre ->
                    genre.name.let{ name ->
                        chipGenre.addView(createTagChip(itemView.context, name, chipGenre), index)
                    }
                }
                itemView.setOnClickListener {
                    listener(game)
                }
            }
        }
    }

    private fun createTagChip(context: Context, chipName: String, chipGroup: ChipGroup): Chip {
        val chip = LayoutInflater.from(context).inflate(R.layout.item_chip, chipGroup, false) as Chip
        chip.text = chipName
        return chip
    }
}