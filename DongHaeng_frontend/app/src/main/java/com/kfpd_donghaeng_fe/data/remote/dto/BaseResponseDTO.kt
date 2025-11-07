package com.kfpd_donghaeng_fe.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BaseResponseDto<T>(
    // API 성공 여부
    @SerializedName("success") val success: Boolean,

    // 성공 시에만 값이 들어가는 실제 데이터 페이로드.
    // 실패 시에는 null이 되므로 Nullable로 선언합니다.
    @SerializedName("data") val data: T? = null,

    // 성공 시에만 들어가는 메시지 (선택적)
    @SerializedName("message") val message: String? = null,

    // 실패 시에만 값이 들어가는 오류 상세 정보.
    // 성공 시에는 null이 되므로 Nullable로 선언합니다.
    @SerializedName("error") val error: ErrorDto? = null
)