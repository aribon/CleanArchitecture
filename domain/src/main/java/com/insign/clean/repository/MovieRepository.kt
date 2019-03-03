package com.insign.clean.repository

import com.insign.clean.domain.model.MovieEntity
import io.reactivex.Single

/**
 * Created by anthony.ribon
 * On 01/03/2019
 */
interface MovieRepository {
  fun searchMovie(search: String): Single<Collection<MovieEntity>>
  fun getMovie(id: String): Single<MovieEntity>
}