package com.kfpd_donghaeng_fe.data.remote.api

import com.kfpd_donghaeng_fe.data.remote.dto.BaseResponseDto
import com.kfpd_donghaeng_fe.data.remote.dto.RequestDetailDto
import retrofit2.http.GET
import retrofit2.http.Path


// 요청 상세 정보
interface RequestApiService {
    @GET("api/request/detail/{requestId}")
    suspend fun getRequestDetail(
        @Path("requestId") requestId: Long
    ): BaseResponseDto<RequestDetailDto>
}