package com.kfpd_donghaeng_fe.data.remote.dto
import com.google.gson.annotations.SerializedName
import com.kfpd_donghaeng_fe.domain.entity.MatchRequestData


//TODO: 변수 형식 더블체크  필요 , 핸드폰 번호


// 사용자 프로필 정보


//GET /api/companion-requests/{request_id}
data class RequestDetailDto(
    @SerializedName("request_id") val requestId: Long,
    @SerializedName("requester") val request: RequesterDto,
    @SerializedName ("status") val status : String,
    @SerializedName("start_address") val startAddress: String, // 시작 주소 명칭
    @SerializedName("destination_address") val destinationAddress: String, // 목적지 주소 명칭

)
data class RequesterDto(
    //@SerializedName("user_id") val UserID: Int,
    @SerializedName("name") val Name : String,
    @SerializedName ("profile_image_url") val ProfileImageUrl : String,
    @SerializedName ("companion_score") val DHScore : Int,
    )





//ongoigview top sheet의 요청자 정보 (요청 장소, 요청 진행 상태 이름, 동행지수)
fun RequestDetailDto.toDomain() = MatchRequestData(
    startAddress=startAddress,
    destinationAddress=destinationAddress,
    status=status,
    Name =request.Name,
    ProfileImageUrl=request.ProfileImageUrl,
    DHScore=request.DHScore

)


