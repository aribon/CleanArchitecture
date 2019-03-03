package com.insign.clean.domain.model

/**
 * Created by anthony.ribon
 * On 01/03/2019
 */
data class MovieEntity(
    val id: String,
    val title: String,
    val year: String,
    val type: String,
    val poster: String,
    val released: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val language: String,
    val country: String,
    val awards: String,
    val rating: String,
    val votes: String,
    val totalSeasons: String)