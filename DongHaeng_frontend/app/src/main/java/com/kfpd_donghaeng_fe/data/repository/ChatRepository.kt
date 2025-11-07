package com.kfpd_donghaeng_fe.data.repository

import com.kfpd_donghaeng_fe.data.remote.dto.Chat


interface ChatRepository {

    /**
     * 7.2. 특정 매칭의 채팅 히스토리 조회
     */
    suspend fun getChatHistory(
        matchId: Long,
        limit: Int? = 50,
        beforeMessageId: Long? = null
    ): List<Chat>

    /**
     * 7.1. 1:1 채팅 메시지 전송
     */
    suspend fun sendMessage(
        matchId: Long,
        message: String
    ): Chat // 전송 후 서버가 응답하는 메시지 객체를 반환
}