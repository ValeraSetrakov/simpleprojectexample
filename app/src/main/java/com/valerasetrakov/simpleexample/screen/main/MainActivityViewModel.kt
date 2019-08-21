package com.valerasetrakov.simpleexample.screen.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.valerasetrakov.base.setValueIfNotSame
import com.valerasetrakov.simpleexample.screen.base.ProjectActivityViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(application: Application) : ProjectActivityViewModel(application) {

    private val _toolbarTitle = MutableLiveData<CharSequence>()
    val toolbarTitle = _toolbarTitle

    fun setToolbarTitle(toolbarTitle: CharSequence) {
        _toolbarTitle.setValueIfNotSame(toolbarTitle)
    }
}