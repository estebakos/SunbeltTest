package com.estebakos.sunbelt.test.ui.model

data class AnimeDetailUI(
    var animeId: Int,
    var url: String?,
    var imageUrl: String? = null,
    var title: String? = null,
    var airing: Boolean,
    var synopsis: String? = null,
    var type: String? = null,
    var episodes: Int,
    var score: Double,
    var startDate: String? = null,
    var endDate: String? = null,
    var rated: String? = null
)