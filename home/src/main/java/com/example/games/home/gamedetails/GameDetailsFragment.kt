package com.example.games.home.gamedetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.games.core.R
import com.example.games.core.domain.model.Game
import com.example.games.home.databinding.FragmentGameDetailsBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class GameDetailsFragment : Fragment() {

    private val gameDetailsViewModel: GameDetailsViewModel by viewModels()
    private val args: GameDetailsFragmentArgs? by navArgs()
    private lateinit var binding: FragmentGameDetailsBinding
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameDetailsBinding.inflate(inflater, container, false)
        showDetailGame(args?.id.toString())
        return binding.root
    }

    private fun showDetailGame(id: String) {
        gameDetailsViewModel.detailsGame(id).observe(viewLifecycleOwner) { game ->
            with(binding){
                txtTitle.text = game.name
                txtDate.text = game.released
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(game.updated)
                val newFormat = format?.let {
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
                        it
                    )
                }
                txtDateUpdated.text = newFormat
                rate.text = game.rating.toString()
                Glide.with(requireContext())
                    .load(game.background_image)
                    .transform(CenterCrop())
                    .into(imgPoster)
                chipGenre.removeAllViews()
                game.genres.forEachIndexed { index, genre ->
                    genre.name.let{ name ->
                        chipGenre.addView(createTagChip(requireContext(), name, chipGenre), index)
                    }
                }
                var statusFavorite = game.isFavorite
                setStatusFavorite(statusFavorite)
                imgFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    gameDetailsViewModel.setFavoriteGame(game, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
                imgBack.setOnClickListener {
                    navController.navigateUp()
                }
                showScreenshoot(game.short_screenshots)
                showStores(game.stores)
            }
        }
    }

    private fun showScreenshoot(screenshots: List<Game.Screenshots>){
        val screenshotAdapter = ScreenshotAdapter(screenshots){ image ->
            Glide.with(requireContext())
                .load(image)
                .transform(CenterCrop())
                .into(binding.imgPoster)
        }
        binding.rvScreenshots.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvScreenshots.setHasFixedSize(true)
        binding.rvScreenshots.adapter = screenshotAdapter
    }

    private fun showStores(stores: List<Game.Stores>){
        val storesAdapter = StoresAdapter(stores){
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.$it"))
            startActivity(browserIntent)
        }
        binding.rvStores.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvStores.setHasFixedSize(true)
        binding.rvStores.adapter = storesAdapter
    }

    private fun createTagChip(context: Context, chipName: String, chipGroup: ChipGroup): Chip {
        val chip = LayoutInflater.from(context).inflate(R.layout.item_chip, chipGroup, false) as Chip
        chip.text = chipName
        return chip
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.imgFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
        } else {
            binding.imgFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_not_favorite))
        }
    }
}