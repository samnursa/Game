package com.example.games.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.games.core.ui.GameAdapter
import com.example.games.di.FavoriteModuleDependencies
import com.example.games.favorite.databinding.FragmentFavoriteBinding
import com.example.games.favorite.di.DaggerFavoriteComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentFavoriteBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gamesAdapter = GameAdapter{
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.example.games.app/game_details/${it.id}".toUri())
                .build()
            navController.navigate(request)

//            val intent = Intent(activity, DetailGameActivity::class.java)
//            intent.putExtra(DetailGameActivity.EXTRA_DATA, it)
//            startActivity(intent)
        }
        binding.rvGames.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvGames.setHasFixedSize(true)
        binding.rvGames.adapter = gamesAdapter
        viewModel.favoriteGame.observe(viewLifecycleOwner) { game ->
            gamesAdapter.setData(game)
            binding.emptyList.visibility = if (game.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }
}
