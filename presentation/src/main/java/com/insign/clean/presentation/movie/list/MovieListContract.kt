package com.insign.clean.presentation.movie.list

import com.insign.clean.presentation.base.MvpPresenter
import com.insign.clean.presentation.base.MvpView

/**
 * Created by anthony.ribon
 * On 03/03/2019
 */
interface MovieListContract {

  interface View: MvpView<Presenter> {

  }

  interface Presenter: MvpPresenter {

  }
}