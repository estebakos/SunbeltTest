package com.estebakos.sunbelt.test.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebakos.sunbelt.test.base.Output
import com.estebakos.sunbelt.test.domain.usecase.GetAnimeListUseCase
import com.estebakos.sunbelt.test.domain.usecase.SearchAnimeListUseCase
import com.estebakos.sunbelt.test.ui.model.AnimeListUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimeViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase,
    private val searchAnimeListUseCase: SearchAnimeListUseCase
) : ViewModel() {

    private val animeListLiveData: MutableLiveData<List<AnimeListUI>> = MutableLiveData()
    private val emptyItemsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val items: LiveData<List<AnimeListUI>>
        get() = animeListLiveData

    val empty: LiveData<Boolean>
        get() = emptyItemsLiveData

    val error: LiveData<Boolean>
        get() = errorLiveData

    val loading: LiveData<Boolean>
        get() = loadingLiveData

    fun loadItems() {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val output = getAnimeListUseCase.execute()
            if (output is Output.Success) {
                onGetItemsSuccess(output.data)
            } else {
                onGetAnimeListError()
            }
        }
    }

    fun searchAnime(query: String, page: Int = 1) {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val output = searchAnimeListUseCase.execute(query, page)
            if (output is Output.Success) {
                onGetItemsSuccess(output.data)
            } else {
                onGetAnimeListError()
            }
        }
    }

    private fun onGetItemsSuccess(animeList: List<AnimeListUI>) {
        if (animeList.isEmpty()) {
            emptyItemsLiveData.postValue(true)
        } else {
            animeListLiveData.postValue(animeList)
        }

        loadingLiveData.postValue(false)
    }

    private fun onGetAnimeListError() {
        viewModelScope.launch {
            delay(300)
            loadingLiveData.postValue(false)
        }.invokeOnCompletion {
            errorLiveData.postValue(true)
        }
    }
}