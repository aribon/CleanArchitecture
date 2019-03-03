package com.insign.clean.data.provider.network

import com.google.gson.annotations.SerializedName

/**
 * Created by anthony.ribon
 * On 01/03/2019
 */
sealed class Response(@SerializedName("Response") val response: Boolean = false) {

  data class SearchMovieResponse(
      @SerializedName("Search") val searchResult: List<MovieResponse>)
    : Response()

  data class MovieResponse(
      @SerializedName("imdbID") val id: String,
      @SerializedName("Title") val title: String,
      @SerializedName("Year") val year: String,
      @SerializedName("Type") val type: String,
      @SerializedName("Poster") val poster: String,
      @SerializedName("Rated") val rated: String,
      @SerializedName("Released") val released: String,
      @SerializedName("Runtime") val runtime: String,
      @SerializedName("Genre") val genre: String,
      @SerializedName("Director") val director: String,
      @SerializedName("Writer") val writer: String,
      @SerializedName("Actors") val actors: String,
      @SerializedName("Plot") val plot: String,
      @SerializedName("Language") val language: String,
      @SerializedName("Country") val country: String,
      @SerializedName("Awards") val awards: String,
      @SerializedName("imdbRating") val rating: String,
      @SerializedName("imdbVotes") val votes: String,
      @SerializedName("totalSeasons") val totalSeasons: String)
    : Response()
}