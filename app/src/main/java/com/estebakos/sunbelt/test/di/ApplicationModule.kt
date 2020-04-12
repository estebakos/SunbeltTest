package com.estebakos.sunbelt.test.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideApplication(application: Application): Context {
        return application
    }

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "SunbeltPreferences",
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @JvmStatic
    @Singleton
    fun providesConnectivityManager(application: Application): ConnectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}