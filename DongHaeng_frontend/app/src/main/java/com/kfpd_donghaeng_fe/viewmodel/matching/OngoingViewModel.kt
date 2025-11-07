package com.kfpd_donghaeng_fe.viewmodel.matching
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.kfpd_donghaeng_fe.ui.matching.MatchingState
import com.kfpd_donghaeng_fe.ui.matching.DHType
import androidx.compose.material3.Text


// /viewmodel/matching/OngoingViewModel.kt (수정)

// Hilt를 사용한다면 @HiltViewModel과 @Inject를 추가해야 합니다.
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.kfpd_donghaeng_fe.domain.usecase.GetRequesterUseCase


// Hilt를 사용한다고 가정하고 @HiltViewModel 어노테이션 추가
@HiltViewModel
class OngoingViewModel @Inject constructor(
    // ⬅️ Use Case를 주입받습니다.
    private val getRequestDetailUseCase: GetRequesterUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MatchingState())
    val uiState = _uiState.asStateFlow()

    fun nextPage() {
        if (_uiState.value.OngoingPage < 2) {
            _uiState.update { it.copy(OngoingPage = it.OngoingPage + 1) }
        }
    }

    fun previousPage() {
        if (_uiState.value.OngoingPage > 0) {
            _uiState.update { it.copy(OngoingPage = it.OngoingPage - 1) }
        }
    }
}


/*
class OngoingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MatchingState())
    val uiState = _uiState.asStateFlow()

    fun nextPage() {
        if (_uiState.value.OngoingPage < 2) {
            _uiState.update { it.copy(OngoingPage = it.OngoingPage + 1) }
        }
    }

    fun previousPage() {
        if (_uiState.value.OngoingPage > 0) {
            _uiState.update { it.copy(OngoingPage = it.OngoingPage - 1) }
        }
    }


}*/



