package com.insign.clean

import com.insign.clean.presentation.base.BaseFragment
import com.insign.clean.presentation.movie.list.MovieListContract
import com.insign.clean.presentation.movie.list.MovieListContract.Presenter

/**
 * Created by anthony.ribon
 * On 03/03/2019
 */
class MovieListFragment: BaseFragment(), MovieListContract.View {

  private lateinit var presenter: Presenter

  override fun setPresenter(presenter: Presenter) {
    this.presenter = presenter
  }

  override fun findViews() {
    super.findViews()
  }

  override fun initializePresenter() {
    super.initializePresenter()
    MovieListPresenter()
  }

  override fun initializeData() {
    super.initializeData()

  }

  override fun initializeView() {
    super.initializeView()
  }
}