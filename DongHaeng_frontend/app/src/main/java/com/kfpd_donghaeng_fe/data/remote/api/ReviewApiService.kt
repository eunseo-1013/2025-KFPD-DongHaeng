package com.kfpd_donghaeng_fe.data.remote.api

import com.kfpd_donghaeng_fe.data.remote.dto.BasePaginatedResponseDto
import com.kfpd_donghaeng_fe.data.remote.dto.BaseResponseDto
import com.kfpd_donghaeng_fe.data.remote.dto.ReceivedReviewsDataDto
import com.kfpd_donghaeng_fe.data.remote.dto.ReviewRequestDto
import com.kfpd_donghaeng_fe.data.remote.dto.ReviewResponseDto
import retrofit2.http.Body
import retrofit2.http.GET

import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


//리뷰 작성
interface ReviewWriteApiService {
    interface ReviewApiService {
        @POST("/api/reviews")
        suspend fun postReview(
            @Body request: ReviewRequestDto
        ): BaseResponseDto<ReviewResponseDto>
    }
}

//리뷰 조회

interface ReviewApiService {

    @POST("/api/reviews")
    suspend fun postReview(
        @Body request: ReviewRequestDto
    ): BaseResponseDto<ReviewResponseDto> // (이전 명세)

    /**
     * 8.2. 특정 사용자가 받은 후기 목록 조회
     */
    @GET("/api/users/{user_id}/reviews")
    suspend fun getReceivedReviews(
        @Path("user_id") userId: Long,
        @Query("page") page: Int?, // optional
        @Query("per_page") perPage: Int? // optional
    ): BasePaginatedResponseDto<ReceivedReviewsDataDto> // ⬅️ 새로운 DTO 적용
}


