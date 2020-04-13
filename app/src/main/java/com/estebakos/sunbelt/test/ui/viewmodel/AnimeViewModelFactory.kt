package com.estebakos.sunbelt.test.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estebakos.sunbelt.test.domain.usecase.GetAnimeListUseCase
import com.estebakos.sunbelt.test.domain.usecase.SearchAnimeListUseCase
import javax.inject.Inject

class AnimeViewModelFactory @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase,
    private val searchAnimeListUseCase: SearchAnimeListUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeViewModel::class.java)) {
            return AnimeViewModel(
                getAnimeListUseCase,
                searchAnimeListUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}