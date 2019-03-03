package com.insign.clean.data.repository.mapper

import com.insign.clean.data.Mapper
import com.insign.clean.data.provider.network.Response
import com.insign.clean.data.provider.network.Response.MovieResponse
import com.insign.clean.domain.model.MovieEntity

/**
 * Created by anthony.ribon
 * On 01/03/2019
 */
class MovieMapper : Mapper<MovieEntity, Response.MovieResponse>() {

  override fun toEntity(value: MovieResponse): MovieEntity {
    return MovieEntity(
        id = value.id,
        title = value.title,
        year = value.year,
        type = value.type,
        poster = value.poster,
        released = value.released,
        genre = value.genre,
        director = value.director,
        writer = value.writer,
        actors = value.actors,
        language = value.language,
        country = value.country,
        awards = value.awards,
        rating = value.rating,
        votes = value.votes,
        totalSeasons = value.totalSeasons)
  }

  override fun fromEntity(value: MovieEntity): MovieResponse {
    return MovieResponse(
        id = value.id,
        title = value.title,
        year = value.year,
        type = value.type,
        poster = value.poster,
        rated = "",
        released = value.released,
        runtime = "",
        genre = value.genre,
        director = value.director,
        writer = value.writer,
        actors = value.actors,
        plot = "",
        language = value.language,
        country = value.country,
        awards = value.awards,
        rating = value.rating,
        votes = value.votes,
        totalSeasons = value.totalSeasons)
  }
}