package com.example.games.home.game

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.games.core.data.Resource
import com.example.games.core.ui.GameAdapter
import com.example.games.home.databinding.FragmentGamesBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class GamesFragment : Fragment() {

    private val viewModel: GamesViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentGamesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gamesAdapter = GameAdapter{
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.example.games.app/game_details/${it.id}".toUri())
                .build()
            navController.navigate(request)
        }
        binding.rvGames.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvGames.setHasFixedSize(true)
        binding.rvGames.adapter = gamesAdapter

        binding.layoutTextSearch.setEndIconOnClickListener{
            binding.rvGames.visibility = View.GONE
            binding.etSearch.setText("")
            viewModel.query.value = ""
        }

        binding.etSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.query.value = binding.etSearch.text.toString()
                binding.rvGames.visibility = View.GONE
                true
            } else {
                false
            }
        }

        viewModel.game().observe(viewLifecycleOwner) { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        gamesAdapter.submitList(game.data)
                        Timber.d(game.data?.size.toString())
                        binding.rvGames.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.emptyList.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}