package com.insign.clean.domain.usecase

import com.insign.clean.domain.model.MovieEntity
import com.insign.clean.repository.MovieRepository
import io.reactivex.Single

/**
 * Created by anthony.ribon
 * On 01/03/2019
 */
public class GetMovieById(private val repository: MovieRepository) {

  fun execute(id: String): Single<MovieEntity> {
    return repository.getMovie(id)
  }
}