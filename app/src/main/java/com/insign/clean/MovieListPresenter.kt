package com.insign.clean

import com.insign.clean.data.repository.Repository
import com.insign.clean.domain.usecase.GetDefaultMovieList
import com.insign.clean.presentation.movie.list.MovieListContract.Presenter

/**
 * Created by anthony.ribon
 * On 03/03/2019
 */
class MovieListPresenter(useCase: GetDefaultMovieList = GetDefaultMovieList(Repository()))
  : Presenter {



}