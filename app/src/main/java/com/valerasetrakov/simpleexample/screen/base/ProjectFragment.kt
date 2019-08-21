package com.valerasetrakov.simpleexample.screen.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.valerasetrakov.base.ShareViewModelFragment
import com.valerasetrakov.base.loge
import com.valerasetrakov.simpleexample.App
import com.valerasetrakov.simpleexample.BR

abstract class ProjectFragment<VM: ProjectFragmentViewModel, VDB : ViewDataBinding,
        SVM: ViewModel>: ShareViewModelFragment<VM, VDB, SVM>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val variableId: Int = BR.viewmodel

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return App.appComponent.getViewModelFactory()
    }

    override fun onViewModelError(throwable: Throwable) {
        loge(error = throwable)
    }
}