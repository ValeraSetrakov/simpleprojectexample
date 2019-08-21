package com.valerasetrakov.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment: Fragment() {

    protected fun requestPermission(permission: String, requestPermissionCode: Int): Boolean {
        val isPermissionGranted = context?.let {
            ContextCompat.checkSelfPermission(it, permission) == PackageManager.PERMISSION_GRANTED
        } ?: false
        if (!isPermissionGranted)
            requestPermissions(arrayOf(permission), requestPermissionCode)
        return isPermissionGranted
    }

    protected val appCompatActivity
        get() = requireActivity() as AppCompatActivity

    /** These method need for setting fragment's toolbar like action bar */
    protected open fun setFragmentSupportActionBar(toolbar: Toolbar) {
        appCompatActivity.setSupportActionBar(toolbar)
    }

    protected open fun getFragmentSupportActionBar() =
        appCompatActivity.supportActionBar

    protected open fun onError(e: Throwable) {
        loge(error = e)
    }

}

abstract class DataBindingFragment<VDB : ViewDataBinding>: BaseFragment() {

    protected lateinit var binding: VDB

    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return this.binding.root
    }

}

abstract class ViewModelFragment <VM: FragmentViewModel, VDB : ViewDataBinding>: DataBindingFragment<VDB>() {

    protected lateinit var viewmodel: VM
    protected lateinit var viewmodelFactory: ViewModelProvider.Factory

    abstract val viewModelType : Class<VM>
    abstract val variableId : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodelFactory = getViewModelFactory()
        viewmodel = ViewModelProviders.of(this, viewmodelFactory).get(viewModelType)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(variableId, viewmodel)

        return view
    }

    open fun getViewModelFactory(): ViewModelProvider.Factory = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
}

abstract class ErrorFragment<VM: FragmentViewModel, VDB : ViewDataBinding>: ViewModelFragment<VM, VDB>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        this.viewmodel.throwable.observe(viewLifecycleOwner, Observer {
            onViewModelError(it)
        })
        return view
    }

    abstract fun onViewModelError (throwable: Throwable)
}

abstract class ShareViewModelFragment<VM: FragmentViewModel, VDB : ViewDataBinding, SVM: ViewModel>: ErrorFragment<VM, VDB>() {

    protected lateinit var shareViewmodel: SVM
    abstract val shareViewModelType: Class<SVM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shareViewmodel = ViewModelProviders.of(requireActivity(), viewmodelFactory).get(shareViewModelType)
    }
}