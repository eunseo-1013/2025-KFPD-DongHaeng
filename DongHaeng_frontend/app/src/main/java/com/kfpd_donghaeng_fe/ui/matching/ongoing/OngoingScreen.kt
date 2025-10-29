package com.kfpd_donghaeng_fe.ui.matching.ongoing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kfpd_donghaeng_fe.viewmodel.matching.OngoingViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OngoingScreen(viewModel: OngoingViewModel = viewModel(),  onNavigateToReview: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {

        // 2️⃣ 상단 시트 (유저 프로필)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .zIndex(1f) // 상단 시트를 위로
                .padding(0.dp)

        ) {
            TopSheet(uiState.OngoingPage)
        }

        // 3️⃣ 하단 시트 (QR 인증 or 동행)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BottomSheet(viewModel, onNavigateToReview) // or OngoingScreen()
        }
    }
}
