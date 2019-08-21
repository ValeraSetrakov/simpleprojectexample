package com.valerasetrakov.base

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.setValueIfNotSame(newValue: T) {
    val oldValue = value
    if (oldValue != newValue)
        value = newValue
}