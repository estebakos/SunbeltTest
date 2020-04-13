package com.estebakos.sunbelt.test.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_list_entity")
data class AnimeListEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "anime_id")
    var animeId: Int,
    @ColumnInfo(name = "image_url")
    var imageUrl: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "synopsis")
    var synopsis: String
)