package com.estebakos.sunbelt.test.domain.usecase

import com.estebakos.sunbelt.test.base.Output
import com.estebakos.sunbelt.test.domain.repository.AnimeRepository
import com.estebakos.sunbelt.test.ui.model.AnimeListUI
import java.io.IOException
import javax.inject.Inject

class SearchAnimeListUseCase @Inject constructor(private val animeRepository: AnimeRepository) {

    suspend fun execute(query: String, page: Int = 1): Output<List<AnimeListUI>> {
        var animeListOutput: Output<List<AnimeListUI>>

        animeRepository.getAnimeList("%$query%", page).let { output ->
            animeListOutput = if (output is Output.Success) {
                animeRepository.insertAnimeList(output.data)
                Output.Success(output.data)
            } else {
                Output.Error(IOException())
            }
        }

        if (animeListOutput is Output.Error) {
            animeRepository.searchLocalAnimeList("%$query%").let { output ->
                animeListOutput = if (output is Output.Success) {
                    Output.Success(output.data)
                } else {
                    Output.Error(IOException())
                }
            }
        }

        return animeListOutput
    }
}