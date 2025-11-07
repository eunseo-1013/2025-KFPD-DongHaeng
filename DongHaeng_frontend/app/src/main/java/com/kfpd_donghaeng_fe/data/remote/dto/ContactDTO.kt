package com.kfpd_donghaeng_fe.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.kfpd_donghaeng_fe.domain.entity.matching.UserPhone


/*
// 매칭자와 연락 ( 전화 )
//GET /api/users/{user_id}
data class UserDto(
    @SerializedName("user_id") val userId: Long,
    @SerializedName("phone") val phone: String,
    @SerializedName ("profile_image_url") val profileImageUrl : String,
    @SerializedName("user_type") val userType: String,
    @SerializedName("destination_address") val destinationAddress: String,
)


fun UserDto.toDomain()= UserPhone(
    Requestphone = phone
)


/*

// 매칭자와 연락 ( 채팅 전송 및 내역 조회 ) <-  TODO :  공부 필요
//GET /api/matches/{match_id}/messages



import java.time.ZonedDateTime

/**
 * 채팅 메시지를 나타내는 도메인 모델
 * (Domain Entity/Value Object)
 */
data class Chat(
    val messageId: Long,
    val matchId: Long,
    val senderId: Long,
    val senderName: String,
    val content: String,
    val isRead: Boolean,
    val readAt: ZonedDateTime?, // 읽은 시간 (null 허용)
    val createdAt: ZonedDateTime // 생성 시간
)

fun ChatMessageDto.toDomain()= ChatMessageData(

)*/