package com.estebakos.sunbelt.test.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, NetworkModule::class, DataModule::class, ActivityModule::class, FragmentModule::class, CacheModule::class])
interface ApplicationComponent : AndroidInjector<com.estebakos.sunbelt.test.Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

}