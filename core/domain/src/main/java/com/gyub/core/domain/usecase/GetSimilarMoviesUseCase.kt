package com.gyub.core.domain.usecase

import com.gyub.core.domain.model.MovieModel
import com.gyub.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 유사 영화 가져오는 UseCase
 *
 * @author   Gyub
 * @created  2024/08/30
 */
class GetSimilarMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    /**
     * 첫 페이지의 유사 영화들만 가져오기
     *
     * @param movieId
     * @param page
     */
    suspend operator fun invoke(movieId: Int, page: Int = 1): Flow<List<MovieModel>> = flow {
        val items = repository.getSimilarMovies(page = page, movieId = movieId)
        emit(items)
    }

}