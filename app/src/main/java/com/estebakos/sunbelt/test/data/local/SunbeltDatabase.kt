package com.estebakos.sunbelt.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.estebakos.sunbelt.test.data.local.entity.AnimeEntity

@Database(
    entities = [AnimeEntity::class],
    version = 1
)
abstract class SunbeltDatabase : RoomDatabase() {

}