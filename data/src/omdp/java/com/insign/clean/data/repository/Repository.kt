package com.insign.clean.data.repository

import com.insign.clean.data.provider.network.Service
import com.insign.clean.data.repository.mapper.MovieMapper
import com.insign.clean.domain.model.MovieEntity
import com.insign.clean.repository.MovieRepository
import io.reactivex.Single

/**
 * Created by anthony.ribon
 * On 01/03/2019
 */
class Repository: MovieRepository {

  override fun searchMovie(search: String): Single<Collection<MovieEntity>> {
    return Service()
        .searchMovie(search)
        .map { MovieMapper().toEntity(it.searchResult) }
  }

  override fun getMovie(id: String): Single<MovieEntity> {
    return Service()
        .getMovie(id)
        .map { MovieMapper().toEntity(it) }
  }

}