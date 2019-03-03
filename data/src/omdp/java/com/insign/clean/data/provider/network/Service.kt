package com.insign.clean.data.provider.network

import com.insign.clean.data.BuildConfig
import com.insign.clean.data.provider.network.Response.MovieResponse
import com.insign.clean.data.provider.network.Response.SearchMovieResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by anthony.ribon
 * On 01/03/2019
 */
class Service {

  private fun getApi(
      apiUrl: String,
      timeOut: Long = 0L): Api {
    return Api.Factory().create(apiUrl, timeOut)
  }

  fun searchMovie(search: String): Single<SearchMovieResponse> {
    return getApi(BuildConfig.API_URL)
        .searchMovie(BuildConfig.API_KEY, search)
        .subscribeOn(Schedulers.io())
  }

  fun getMovie(id: String): Single<MovieResponse> {
    return getApi(BuildConfig.API_URL)
        .getMovie(BuildConfig.API_KEY, id)
        .subscribeOn(Schedulers.io())
  }
}