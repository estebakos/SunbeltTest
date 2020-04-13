package com.estebakos.sunbelt.test.data.remote.model

import com.squareup.moshi.Json

data class AnimeDetailResponse(
    @field:Json(name = "anime_id")
    var animeId: Int,
    @field:Json(name = "url")
    var url: String?,
    @field:Json(name = "image_url")
    var imageUrl: String? = null,
    @field:Json(name = "title")
    var title: String? = null,
    @field:Json(name = "airing")
    var airing: Boolean,
    @field:Json(name = "synopsis")
    var synopsis: String? = null,
    @field:Json(name = "type")
    var type: String? = null,
    @field:Json(name = "episodes")
    var episodes: Int? = 0,
    @field:Json(name = "score")
    var score: Double,
    @field:Json(name = "start_date")
    var startDate: String? = null,
    @field:Json(name = "end_date")
    var endDate: String? = null,
    @field:Json(name = "rated")
    var rated: String? = null
)