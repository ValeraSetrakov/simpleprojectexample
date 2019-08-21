package com.valerasetrakov.base

import timber.log.Timber

val Any.tag get(): String {
    var tag = this::class.java.simpleName
    if (tag.isEmpty()) {
        val superclass = this::class.java.superclass
        tag = if (superclass != null)
            superclass.simpleName
        else
            this.toString()
    }
    return tag
}
fun Any.logd(message: String, tag: String = this.tag) {
    val TAG = tag
    Timber.tag(TAG).d(message)
}
fun Any.loge(message: String = "", error: Throwable, tag: String = this.tag) {
    val TAG = tag
    Timber.tag(TAG).e(error, message)
}