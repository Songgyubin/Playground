package com.gyub.feature.detail.model

import com.gyub.core.domain.model.MovieCreditsModel
import com.gyub.core.domain.model.MovieDetailModel

/**
 * 영화 상세 UI State
 *
 * @author   Gyub
 * @created  2024/08/20
 */
sealed interface MovieDetailUiState {
    data object Error : MovieDetailUiState

    data object Loading : MovieDetailUiState

    data class Success(
        val movieDetail: MovieDetailModel,
        val movieCredits: MovieCreditsModel,
    ) : MovieDetailUiState
}