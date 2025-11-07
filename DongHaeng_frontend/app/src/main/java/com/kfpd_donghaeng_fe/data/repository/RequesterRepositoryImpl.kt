package com.kfpd_donghaeng_fe.data.repository

import com.kfpd_donghaeng_fe.data.remote.api.RequestApiService
import com.kfpd_donghaeng_fe.data.remote.dto.toDomain
import com.kfpd_donghaeng_fe.domain.entity.MatchRequestData
import com.kfpd_donghaeng_fe.domain.repository.RequesterRepository
import jakarta.inject.Inject

// /data/repository/RequestRepositoryImpl.kt
// ➡️ [수정 1] Repository 구현체 및 반환 타입을 'RequestDetailEntity'로 변경해야 합니다.

class RequesterRepositoryImpl @Inject constructor (
    private val apiService: RequestApiService
) : RequesterRepository {
    override suspend fun getRequestDetail(requestId: Long): MatchRequestData {
        val response = apiService.getRequestDetail(requestId)
        val requestDetailDto = response.data ?: throw Exception("요청 상세 데이터 없음")
        return requestDetailDto.toDomain()
    }
}
