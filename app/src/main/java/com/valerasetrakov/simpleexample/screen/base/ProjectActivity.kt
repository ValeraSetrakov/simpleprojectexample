package com.valerasetrakov.simpleexample.screen.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.valerasetrakov.base.FragmentActivity
import com.valerasetrakov.base.loge
import com.valerasetrakov.simpleexample.App
import com.valerasetrakov.simpleexample.BR

abstract class ProjectActivity<VM : ProjectActivityViewModel, VDB : ViewDataBinding> : FragmentActivity<VM, VDB>() {

    override val variableId: Int = BR.viewmodel

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return App.appComponent.getViewModelFactory()
    }

    override fun onViewModelError(throwable: Throwable) {
        loge(error = throwable)
    }
}