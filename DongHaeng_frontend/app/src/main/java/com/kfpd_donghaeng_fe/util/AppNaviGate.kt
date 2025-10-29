package com.kfpd_donghaeng_fe.util

import androidx.navigation.NavController
import com.kfpd_donghaeng_fe.ui.auth.UserType
import android.util.Log
// 모든 화면 경로를 여기에 정의
object AppScreens {
    // Auth Flow
    const val SIGN_UP = "signup"

    // Home Flow
    // {userType} 인자를 포함하는 Home 경로
    const val HOME_BASE = "home"
    const val HOME_ROUTE = "$HOME_BASE/{userType}"

    // Matching Flow
    const val MATCHING_BASE = "matching"
    const val MATCHING_ROUTE = "$MATCHING_BASE/{userType}"
    const val REQUEST_DETAIL_SCREEN = "request_detail_route/{requestId}"
    const val REVIEW_SCREEN = "review_route"

    // Matching Ongoing Flow
    const val ONGOING_SCREEN = "ongoing_route"
}

/**
 * 모든 화면으로 이동할 수 있는 범용 함수.
 * @param route 이동할 목적지 경로 (예: AppScreens.MY_PAGE)
 * @param popUpToRoute 백 스택에서 제거할 기준 경로 (제거하지 않으려면 null)
 * @param inclusive popUpToRoute까지 제거할지 여부
 * @param singleTop 해당 경로가 이미 스택에 있으면 새 인스턴스를 만들지 않을지 여부
 */
fun NavController.navigateTo(
    route: String,
    popUpToRoute: String? = null,
    inclusive: Boolean = false,
    singleTop: Boolean = false
) {
    this.navigate(route) {
        if (popUpToRoute != null) {
            popUpTo(popUpToRoute) { this.inclusive = inclusive }
        }
        this.launchSingleTop = singleTop
    }
}

/**
 * 회원가입 완료 후 메인 홈 화면으로 이동, 백 스택 정리
 * @param userType 가입한 사용자 유형
 */
fun NavController.navigateToHomeAfterSignUp(userType: UserType) {
    // UserType을 인자로 포함하여 Home 화면으로 이동합니다. (예: home/NEEDY)
    val routeWithArg = "${AppScreens.HOME_BASE}/${userType.name}"

    this.navigateTo(
        route = routeWithArg,
        popUpToRoute = AppScreens.SIGN_UP, // 회원가입 스택 전체 제거
        inclusive = true
    )
}

/**
 * 메인 화면에서 '요청 확인하기/요청하기' 버튼 클릭 시, 매칭 화면으로 이동
 * @param userType 현재 사용자의 유형 (NEEDY 또는 HELPER)
 */
fun NavController.navigateToMatchingScreen(userType: UserType) {
    // ✅ UserType을 인자로 포함하여 Matching 화면으로 이동합니다.
    val routeWithArg = "${AppScreens.MATCHING_BASE}/${userType.name}"

    // 매칭 화면으로 이동합니다.
    this.navigateTo(routeWithArg)
}

/**
 * 특정 요청의 상세 화면으로 이동합니다.
 * @param requestId 요청의 고유 ID
 */
fun NavController.navigateToRequestDetail(requestId: Long) {
    // 인자를 포함하여 경로를 생성합니다. (예: request_detail_route/123)
    val routeWithArg = AppScreens.REQUEST_DETAIL_SCREEN.replace(
        "{requestId}",
        requestId.toString()
    )

    Log.d("NAV_DEBUG", "NAVIGATING TO: $routeWithArg")
    this.navigateTo(routeWithArg)
}

/**
 * 리뷰 작성 화면으로 이동합니다.
 */
fun NavController.navigateToReviewScreen() {
    this.navigateTo(AppScreens.REVIEW_SCREEN)
}

/**
 * 동행 중 화면 (OngoingScreen)으로 이동합니다.
 */
fun NavController.navigateToOngoingScreen() {
    // 다른 화면 스택을 정리해야 하는지 여부에 따라 popUpTo 설정을 추가할 수 있습니다.
    // 예: 매칭 플로우를 모두 제거하고 이동하는 경우
    this.navigateTo(
        route = AppScreens.ONGOING_SCREEN,
        popUpToRoute = AppScreens.HOME_BASE,
        inclusive = true,
        singleTop = true
    )
}