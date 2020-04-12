package com.estebakos.sunbelt.test.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.estebakos.sunbelt.test.data.local.entity.AnimeEntity

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime_entity")
    suspend fun getAll(): List<AnimeEntity>

    @Query("SELECT * FROM anime_entity WHERE anime_id = :animeId")
    suspend fun findByCode(animeId: String): AnimeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<AnimeEntity>)

    @Query("DELETE FROM anime_entity")
    suspend fun deleteAll()
}