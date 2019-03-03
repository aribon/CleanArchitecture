package com.insign.clean.data.provider.network

import com.insign.clean.data.provider.network.Response.MovieResponse
import com.insign.clean.data.provider.network.Response.SearchMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by anthony.ribon
 * On 01/03/2019
 */
interface Api {

  class Factory {
    fun create(apiUrl: String, timeOut: Long): Api {
      val retrofit = RetrofitHelper
          .buildRetrofit(
              apiUrl = apiUrl,
              timeOut = timeOut)
      return retrofit.create(Api::class.java)
    }
  }

  @GET
  fun searchMovie(
      @Query("apiKey") apiKey: String,
      @Query("s") search: String)
  : Single<SearchMovieResponse>

  @GET
  fun getMovie(
      @Query("apiKey") apiKey: String,
      @Query("i") id: String)
  : Single<MovieResponse>
}
