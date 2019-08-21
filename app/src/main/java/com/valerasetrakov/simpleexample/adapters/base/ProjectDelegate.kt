package com.valerasetrakov.simpleexample.adapters.base

import androidx.databinding.ViewDataBinding
import com.valerasetrakov.base.BaseDelegate
import com.valerasetrakov.simpleexample.BR

abstract class ProjectDelegate<IT: Any, DB: ViewDataBinding>
    (layoutId: Int, onItemClickListener: OnItemClickListener<IT>? = null)
    : BaseDelegate<IT, DB>(layoutId, variableId = BR.viewmodel, onItemClickListener = onItemClickListener)