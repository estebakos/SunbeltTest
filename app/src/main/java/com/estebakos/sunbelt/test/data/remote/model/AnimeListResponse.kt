package com.estebakos.sunbelt.test.data.remote.model

import com.squareup.moshi.Json

data class AnimeListResponse(
    @field:Json(name = "results")
    val results: List<AnimeListResponseItem> = listOf()
)