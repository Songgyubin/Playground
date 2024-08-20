package com.gyub.feature.detail.model

import com.gyub.feature.detail.R

/**
 * 영화 개봉 상태
 *
 * @author   Gyub
 * @created  2024/08/22
 */
enum class MovieStatus(val displayName: Int, val originalName: String) {
    Released(R.string.feature_detail_released, "Released"),
    ComingSoon(R.string.feature_detail_coming_soon, "ComingSoon");

    companion object {
        fun getMovieStatusByOriginalName(originalName: String): MovieStatus {
            return entries.find { it.originalName.lowercase() == originalName.lowercase() } ?: Released
        }
    }
}