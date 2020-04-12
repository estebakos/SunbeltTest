package com.estebakos.sunbelt.test.di

import dagger.Module

@Module(includes = [NetworkModule::class])
object DataModule {

    /*@Provides
    @JvmStatic
    internal fun providesConfigRepository(configRemoteDataSource: ConfigRemoteDataSource): ConfigRepository =
        ConfigRepositoryImpl(configRemoteDataSource)*/
}