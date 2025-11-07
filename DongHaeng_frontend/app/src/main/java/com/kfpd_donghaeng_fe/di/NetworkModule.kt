package com.kfpd_donghaeng_fe.di

import com.kfpd_donghaeng_fe.data.remote.api.KakaoPlaceApiService
import com.kfpd_donghaeng_fe.data.remote.api.RequestApiService
import com.kfpd_donghaeng_fe.data.repository.PlaceRepositoryImpl
import com.kfpd_donghaeng_fe.domain.repository.PlaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("kakao")
    fun provideKakaoRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/v2/local/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideKakaoPlaceApiService(
        @Named("kakao") retrofit: Retrofit
    ): KakaoPlaceApiService {
        return retrofit.create(KakaoPlaceApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePlaceRepository(
        apiService: KakaoPlaceApiService
    ): PlaceRepository {
        return PlaceRepositoryImpl(apiService)
    }
/*
    @Provides
    @Singleton
    fun provideRequestApiService(retrofit: Retrofit): RequestApiService {
        // ⬅️ Retrofit의 .create() 메서드를 사용하여 인터페이스의 구현체를 Hilt에게 제공합니다.
        return retrofit.create(RequestApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // ⬅️ [2. 새로 추가] 일반 Retrofit 객체 제공 (메인 서버 통신용)
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.yourserver.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/

}