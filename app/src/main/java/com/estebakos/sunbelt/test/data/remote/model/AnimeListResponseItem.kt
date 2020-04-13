package com.estebakos.sunbelt.test.data.remote.model

import com.squareup.moshi.Json

data class AnimeListResponseItem(
    @field:Json(name = "mal_id")
    val animeId: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "synopsis")
    val synopsis: String,
    @field:Json(name = "image_url")
    val imageUrl: String
)