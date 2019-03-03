package com.insign.clean.presentation.base

/**
 * Created by anthony.ribon
 * On 03/03/2019
 */
interface MvpView<P: MvpPresenter> {
  fun setPresenter(presenter: P)
}