package com.estebakos.sunbelt.test.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.estebakos.sunbelt.test.R
import com.estebakos.sunbelt.test.ui.model.AnimeDetailUI
import com.estebakos.sunbelt.test.ui.viewmodel.AnimeViewModel
import com.estebakos.sunbelt.test.ui.viewmodel.AnimeViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_anime_detail.*
import javax.inject.Inject

class AnimeDetailFragment : Fragment(R.layout.fragment_anime_detail) {

    @Inject
    lateinit var animeViewModelFactory: AnimeViewModelFactory
    private lateinit var animeViewModel: AnimeViewModel
    private val args: AnimeDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animeViewModel = ViewModelProvider(
            requireActivity(), animeViewModelFactory
        ).get(AnimeViewModel::class.java)

        observeLiveData()
        animeViewModel.getAnimeById(args.anime.animeId)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeLiveData() {
        animeViewModel.animeDetail.observe(requireActivity(), Observer(::onAnimeDetailReceived))
        animeViewModel.loading.observe(requireActivity(), Observer(::onLoadingStateReceived))
        animeViewModel.empty.observe(requireActivity(), Observer(::onEmptyReceived))
        animeViewModel.error.observe(requireActivity(), Observer(::onErrorReceived))
    }

    private fun onAnimeDetailReceived(animeDetailUI: AnimeDetailUI) {
        if (isAdded && iv_anime_detail != null) {
            Glide.with(requireActivity()).load(animeDetailUI.imageUrl).into(iv_anime_detail)
            tv_anime_detail_episodes.text =
                getString(R.string.anime_detail_episodes, animeDetailUI.episodes)
            tv_anime_detail_rate.text = getString(R.string.anime_detail_rate, animeDetailUI.score)
            tv_anime_detail_title.text = animeDetailUI.title
            tv_anime_detail_synopsis.text = animeDetailUI.synopsis
        }
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        if (isAdded && loading_detail_layout != null) {
            loading_detail_layout.apply {
                visibility = if (isLoading) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }
    }

    private fun onErrorReceived(hasInternet: Boolean) {
        if (isAdded) {
            Toast.makeText(requireContext(), getString(R.string.no_results), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun onEmptyReceived(hasInternet: Boolean) {
        if (isAdded) {
            Toast.makeText(requireContext(), getString(R.string.no_results), Toast.LENGTH_SHORT)
                .show()
        }
    }
}