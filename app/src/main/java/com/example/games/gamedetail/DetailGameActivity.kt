package com.example.games.gamedetail

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.games.R
import com.example.games.core.domain.model.Game
import com.example.games.databinding.ActivityDetailGameBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Suppress("DEPRECATION")
class DetailGameActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailGameViewModel: DetailGameViewModel by viewModels()
    private lateinit var binding: ActivityDetailGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailGame = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, Game::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_DATA)
        }
        showDetailGame(detailGame)
    }

    private fun showDetailGame(detailGame: Game?) {
        detailGame?.let {
            with(binding){
                txtTitle.text = it.name
                txtDate.text = it.released
                rate.text = it.rating.toString()
                Glide.with(this@DetailGameActivity)
                    .load(it.background_image)
                    .transform(CenterCrop())
                    .into(imgPoster)
                it.genres.forEachIndexed { index, genre ->
                    genre.name.let{ name ->
                        chipGenre.addView(createTagChip(this@DetailGameActivity, name, chipGenre), index)
                    }
                }
                var statusFavorite = detailGame.isFavorite
                setStatusFavorite(statusFavorite)
                imgFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailGameViewModel.setFavoriteGame(detailGame, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
                imgBack.setOnClickListener {
                    finish()
                }
            }
        }
    }

    private fun createTagChip(context: Context, chipName: String, chipGroup: ChipGroup): Chip {
        val chip = LayoutInflater.from(context).inflate(R.layout.item_chip, chipGroup, false) as Chip
        chip.text = chipName
        return chip
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.imgFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.imgFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite))
        }
    }
}