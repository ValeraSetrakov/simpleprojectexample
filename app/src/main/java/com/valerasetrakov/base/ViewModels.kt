package com.valerasetrakov.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel(application: Application): AndroidViewModel(application) {
    protected val app = application
    protected val TAG = javaClass.simpleName
}

abstract class LoadViewModel(application: Application): BaseViewModel(application) {
    private val _isLoad = MutableLiveData<Boolean>().apply { value = false }
    val isLoad: LiveData<Boolean> = this._isLoad
    fun startLoad() = this._isLoad.apply { value = true }
    fun endLoad() = this._isLoad.apply { value = false }
    open fun reload() {}
}

abstract class ErrorViewModel(application: Application): LoadViewModel(application) {

    private val _throwable = SingleLiveEvent<Throwable>()
    val throwable: LiveData<Throwable> = this._throwable

    open fun onError(throwable: Throwable) {
        this._throwable.value = throwable
    }
}

abstract class ActivityViewModel(application: Application): ErrorViewModel(application)
abstract class FragmentViewModel(application: Application): ErrorViewModel(application)
abstract class DialogViewModel(application: Application): FragmentViewModel(application)


inline fun ErrorViewModel.invokeAsyncOperation(crossinline block: suspend ()->Unit) = viewModelScope.launch {
    try {
        startLoad()
        block()
    } catch (e: Throwable) {
        onError(e)
    } finally {
        endLoad()
    }
}