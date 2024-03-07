package com.p3l.gah_mobile.di

import android.content.Context
import com.p3l.gah_mobile.data.api.ApiConfig
import com.p3l.gah_mobile.data.api.ApiService
import com.p3l.gah_mobile.repository.AuthRepository
import com.p3l.gah_mobile.repository.InternalRepository
import com.p3l.gah_mobile.repository.MainRepository
import com.p3l.gah_mobile.repository.ReservasiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideUsersRepository(
        userApi : ApiService,
    ) = AuthRepository(userApi)

    @Singleton
    @Provides
    fun provideMainRepository(
        authApi : ApiService,
    ) = MainRepository(authApi)

    @Singleton
    @Provides
    fun provideReservasiRepository(
        authApi : ApiService,
    ) = ReservasiRepository(authApi)

    @Singleton
    @Provides
    fun provideInternalRepository(
        authApi : ApiService,
    ) = InternalRepository(authApi)

    @Singleton
    @Provides
    fun provideApiService() : ApiService {
        return ApiConfig.getApiService()
    }

    @Provides
    fun provideContext(
        @ApplicationContext
        context: Context
    ) = context
}