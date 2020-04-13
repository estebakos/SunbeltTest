package com.estebakos.sunbelt.test.domain.usecase

import com.estebakos.sunbelt.test.base.Output
import com.estebakos.sunbelt.test.domain.repository.AnimeRepository
import com.estebakos.sunbelt.test.ui.model.AnimeDetailUI
import javax.inject.Inject

class GetAnimeByIdUseCase @Inject constructor(private val animeRepository: AnimeRepository) {

    suspend fun execute(id: Int): Output<AnimeDetailUI> = animeRepository.getAnime(id)
}