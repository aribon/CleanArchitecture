package com.insign.clean.presentation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by anthony.ribon
 * On 03/03/2019
 */
open class BaseFragment: Fragment() {

  protected open fun findViews() {

  }

  protected open fun initializePresenter() {

  }

  protected open fun initializeData() {

  }

  protected open fun initializeView() {

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initializePresenter()
  }

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    findViews()
    initializeData()
    initializeView()
  }
}