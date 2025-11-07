package com.kfpd_donghaeng_fe.di
// /di/RepositoryModule.kt (새로 생성)

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.kfpd_donghaeng_fe.domain.repository.RequesterRepository
import com.kfpd_donghaeng_fe.data.repository.RequesterRepositoryImpl // ⬅️ 이 구현체도 필요

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule { // ⬅️ @Binds를 사용하려면 abstract여야 합니다.

    @Binds
    @Singleton
    // Hilt야, RequesterRepository 인터페이스가 필요하면 RequesterRepositoryImpl를 바인딩해줘.
    abstract fun bindRequesterRepository(
        repositoryImpl: RequesterRepositoryImpl
    ): RequesterRepository
}