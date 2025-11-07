package com.kfpd_donghaeng_fe.domain.repository

import com.kfpd_donghaeng_fe.domain.entity.MatchRequestData

interface RequesterRepository {
    suspend fun getRequestDetail(requestId: Long): MatchRequestData
}