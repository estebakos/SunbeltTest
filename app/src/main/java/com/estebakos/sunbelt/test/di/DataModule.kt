package com.estebakos.sunbelt.test.di

import com.estebakos.sunbelt.test.data.AnimeRepositoryImpl
import com.estebakos.sunbelt.test.data.local.datasource.AnimeLocalDataSource
import com.estebakos.sunbelt.test.data.remote.datasource.AnimeRemoteDataSource
import com.estebakos.sunbelt.test.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
object DataModule {

    @Provides
    @JvmStatic
    internal fun providesAnimeRepository(
        animeRemoteDataSource: AnimeRemoteDataSource,
        animeLocalDataSource: AnimeLocalDataSource
    ): AnimeRepository =
        AnimeRepositoryImpl(animeRemoteDataSource, animeLocalDataSource)
}