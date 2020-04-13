package com.estebakos.sunbelt.test.di

import com.estebakos.sunbelt.test.ui.fragment.AnimeDetailFragment
import com.estebakos.sunbelt.test.ui.fragment.AnimeListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindAnimeListFragment(): AnimeListFragment

    @ContributesAndroidInjector
    abstract fun bindAnimeDetailFragment(): AnimeDetailFragment
}