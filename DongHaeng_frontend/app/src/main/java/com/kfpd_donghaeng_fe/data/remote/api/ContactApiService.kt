package com.kfpd_donghaeng_fe.data.remote.api

/*
import com.google.gson.annotations.SerializedName
import com.kfpd_donghaeng_fe.data.remote.dto.BaseResponseDto
import com.kfpd_donghaeng_fe.data.remote.dto.UserDto
import com.kfpd_donghaeng_fe.domain.entity.matching.UserPhone

import retrofit2.http.GET
import retrofit2.http.Path

interface UserPhoneService {
    @GET("/api/users/{user_id}")
    suspend fun getUserDetails(
        @Path("user_id") userId: Long
    ): UserDto
}
/*

//chatting
interface ContactApiService {
    @GET("/api/matches/{match_id}/messages")
    suspend fun getMatchChatHistory(
        @Path("match_id") matchId: Long, // URL Path 변수
        @Query("limit") limit: Int? = 50, // Query Parameter (optional)
        @Query("before") beforeMessageId: Long? = null // Query Parameter (cursor)
    ): BaseResponseDto<ChatMessageDto>
}
*/




