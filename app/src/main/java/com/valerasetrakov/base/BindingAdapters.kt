package com.valerasetrakov.base

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("isRefreshing")
fun setRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean?) = view.apply {
    if (isRefreshing == null)
        view.isRefreshing = false
    else
        view.isRefreshing = isRefreshing
}


@BindingAdapter("onRefresh")
fun setOnRefresh(view: SwipeRefreshLayout, callback: SwipeRefreshLayout.OnRefreshListener) = view.setOnRefreshListener(callback)

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun setImageUrl(view: ImageView, url: String?, placeholder: Drawable?) {
    val request = GlideApp.with(view).load(url)
    if (placeholder != null)
        request.placeholder(placeholder)
    request.into(view)
}

@BindingAdapter("menu")
fun setMenu (view: Toolbar, menuId: Int?) {
    menuId?.let {
        view.inflateMenu(menuId)
    }
}

@BindingAdapter("spannableText")
fun setSpannableText(view: TextView, text: CharSequence?) {
    if (text != null)
        view.setText(text, TextView.BufferType.SPANNABLE)
}


@BindingAdapter("startDrawableAdjustTextSize")
fun setStartDrawableAdjustTextSize(view: TextView, drawable: Drawable?) {
    if (drawable != null) {
        val lineHeight = view.lineHeight
        val intrinsicHeight = drawable.intrinsicHeight
        val intrinsicWidth = drawable.intrinsicWidth
        val newDrawableHeight = lineHeight
        val diffsHeight = intrinsicHeight.toFloat() / newDrawableHeight.toFloat()
        val newDrawableWidth = intrinsicWidth / diffsHeight
        drawable.setBounds(0, 0, newDrawableWidth.toInt(), newDrawableHeight)
        view.setCompoundDrawables(drawable, view.compoundDrawables[1], view.compoundDrawables[2], view.compoundDrawables[3])
    }
}

@BindingAdapter("isShowShadow")
fun setIsShowShadow(view: View, isShowShadow: Boolean?) {
    view.outlineProvider = if (isShowShadow == true)
        ViewOutlineProvider.BACKGROUND
    else
        null
}