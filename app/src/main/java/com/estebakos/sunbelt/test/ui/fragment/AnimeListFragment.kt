package com.estebakos.sunbelt.test.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.estebakos.sunbelt.test.R
import com.estebakos.sunbelt.test.ui.adapter.AnimeRecyclerViewAdapter
import com.estebakos.sunbelt.test.ui.model.AnimeListUI
import com.estebakos.sunbelt.test.ui.viewmodel.AnimeViewModel
import com.estebakos.sunbelt.test.ui.viewmodel.AnimeViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_anime_list.*
import javax.inject.Inject

class AnimeListFragment : Fragment(R.layout.fragment_anime_list) {

    @Inject
    lateinit var animeViewModelFactory: AnimeViewModelFactory
    private lateinit var animeViewModel: AnimeViewModel

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
    }

    override fun onResume() {
        super.onResume()
        animeViewModel.loadItems()
    }

    private fun observeLiveData() {
        animeViewModel.items.observe(requireActivity(), Observer(::onAnimeListReceived))
        animeViewModel.loading.observe(requireActivity(), Observer(::onLoadingStateReceived))
        animeViewModel.empty.observe(requireActivity(), Observer(::onEmptyReceived))
        animeViewModel.error.observe(requireActivity(), Observer(::onErrorReceived))
    }

    private fun onAnimeListReceived(list: List<AnimeListUI>) {
        rv_main_data.run {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }

            adapter = AnimeRecyclerViewAdapter(list) {

            }

            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        loadingLayout.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun onErrorReceived(hasInternet: Boolean) {
        // showAlertDialog(getString(R.string.connection_error))
        Toast.makeText(requireContext(), getString(R.string.no_results), Toast.LENGTH_SHORT).show()
    }

    private fun onEmptyReceived(hasInternet: Boolean) {
        // showAlertDialog(getString(R.string.connection_error))
        Toast.makeText(requireContext(), getString(R.string.no_results), Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_main_data.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}