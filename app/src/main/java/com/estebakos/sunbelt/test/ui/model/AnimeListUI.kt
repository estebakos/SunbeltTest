package com.estebakos.sunbelt.test.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimeListUI(
    val animeId: Int,
    val title: String,
    val synopsis: String,
    val imageUrl: String
) : Parcelable