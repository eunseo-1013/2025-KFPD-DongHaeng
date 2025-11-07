package com.kfpd_donghaeng_fe.domain.usecase

import com.kfpd_donghaeng_fe.domain.entity.MatchRequestData
import com.kfpd_donghaeng_fe.domain.repository.RequesterRepository
import jakarta.inject.Inject


class GetRequesterUseCase @Inject constructor(
    private val repository: RequesterRepository // ⬅️ Repository 주입
) {
    suspend operator fun invoke(requestId: Long): MatchRequestData {

        // 1. 비즈니스 규칙 적용 (선택 사항)
        if (requestId <= 0) {
            throw IllegalArgumentException("요청 ID는 양수여야 합니다.")
        }

        // 2. Repository 호출 및 Entity 반환
        return repository.getRequestDetail(requestId)
    }
}