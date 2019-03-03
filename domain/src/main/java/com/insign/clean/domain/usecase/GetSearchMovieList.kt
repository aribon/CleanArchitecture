package com.insign.clean.domain.usecase

import com.insign.clean.domain.model.MovieEntity
import com.insign.clean.repository.MovieRepository
import io.reactivex.Single

/**
 * Created by anthony.ribon
 * On 01/03/2019
 */
public class GetSearchMovieList(private val repository: MovieRepository) {

  fun execute(search: String): Single<Collection<MovieEntity>> {
//    return Single.create<Collection<MovieEntity>> {
//      try {
//        val movieList = repository.searchMovie(search)
//        it.onSuccess(movieList)
//      } catch (e: Exception) {
//        it.onError(e)
//      }
//    }

    return repository.searchMovie(search)
  }
}