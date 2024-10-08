package com.gyub.core.design.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.gyub.core.design.R
import com.gyub.core.design.util.size.TmdbImageSize

/**
 * TMDB Poster AsyncImage
 * TMDB 특성 상 응답으로 오는 poster_path(파일명)에 BaseUrl을 붙여서
 * 이미지를 받아와야 함
 *
 * https://image.tmdb.org/t/p/w200(이미지 크기)/파일명
 *
 * @author   Gyub
 * @created  2024/08/06
 */
@Composable
fun TMDBAsyncImage(
    imageUrl: String,
    tmdbImageSize: TmdbImageSize,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: Int = R.drawable.core_design_loading_img,
    error: Int = R.drawable.core_design_ic_broken_image,
) {
    val posterUrl = BASE_POSTER_URL + tmdbImageSize.toString() + imageUrl

    DefaultAsyncImage(
        imageUrl = posterUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        placeholder = placeholder,
        error = error
    )
}

private val BASE_POSTER_URL: String by lazy { "https://image.tmdb.org/t/p/" }