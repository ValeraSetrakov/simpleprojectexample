package com.valerasetrakov.simpleexample.screen.main

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.valerasetrakov.simpleexample.screen.base.ProjectFragment
import com.valerasetrakov.simpleexample.screen.base.ProjectFragmentViewModel

abstract class MainActivityFragment<VM: ProjectFragmentViewModel, VDB : ViewDataBinding> : ProjectFragment<VM, VDB, MainActivityViewModel>() {

    override val shareViewModelType: Class<MainActivityViewModel> = MainActivityViewModel::class.java
    abstract val toolbarTitleId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbarTitle = getString(toolbarTitleId)
        shareViewmodel.setToolbarTitle(toolbarTitle)
    }
}