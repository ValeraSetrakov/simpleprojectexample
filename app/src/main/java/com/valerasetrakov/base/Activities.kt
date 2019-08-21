package com.valerasetrakov.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.Intent


abstract class BaseActivity : AppCompatActivity() {
    protected fun restart() {
//        if (Build.VERSION.SDK_INT >= 11) {
//            recreate()
//        } else {
//            val intent = intent
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//            finish()
//            overridePendingTransition(0, 0)
//
//            startActivity(intent)
//            overridePendingTransition(0, 0)
//        }

        val intent = intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()
        overridePendingTransition(0, 0)

        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}

abstract class DataBindingActivity<VDB : ViewDataBinding>: BaseActivity() {

    protected lateinit var binding: VDB

    abstract val layoutId : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, this.layoutId)
    }

}

abstract class ViewModelActivity <VM : ActivityViewModel, VDB : ViewDataBinding>: DataBindingActivity<VDB>(){

    protected lateinit var viewmodel: VM

    abstract val viewModelType : Class<VM>
    abstract val variableId : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = getViewModelFactory()
        this.viewmodel = ViewModelProviders.of(this, viewModelFactory).get(this.viewModelType)
        this.binding.lifecycleOwner = this
        this.binding.setVariable(this.variableId, this.viewmodel)

    }

    open fun getViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
    }

}

abstract class ErrorActivity<VM : ActivityViewModel, VDB : ViewDataBinding>: ViewModelActivity<VM, VDB>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewmodel.throwable.observe(this, Observer {
            onViewModelError(it)
        })
    }

    abstract fun onViewModelError (throwable: Throwable)
}

interface FragmentHost {
    val fragmentHostId: Int
}

private const val INDEX_FRAGMENT_ANIMATION_ENTER = 0
private const val INDEX_FRAGMENT_ANIMATION_EXIT = 1
private const val INDEX_FRAGMENT_ANIMATION_POP_ENTER = 2
private const val INDEX_FRAGMENT_ANIMATION_POP_EXIT = 3

abstract class FragmentActivity <VM : ActivityViewModel, VDB : ViewDataBinding> : ErrorActivity<VM, VDB>(),
    FragmentHost {

    open var startFragment: Fragment? = null
    val currentFragment
        get() = supportFragmentManager.findFragmentById(fragmentHostId)
    private val fragmentAnimations = arrayOf(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            openStartFragment(startFragment)
        }
    }

    fun changeFragment (
        fragment: Fragment?,
        addToBackStack: Boolean = false,
        backStackName: String = fragment?.javaClass?.simpleName ?: "") {

        fragment ?: return

        val fragmentHostId = this.fragmentHostId
        supportFragmentManager.transaction {
            setCustomAnimations(
                fragmentAnimations[INDEX_FRAGMENT_ANIMATION_ENTER],
                fragmentAnimations[INDEX_FRAGMENT_ANIMATION_EXIT],
                fragmentAnimations[INDEX_FRAGMENT_ANIMATION_POP_ENTER],
                fragmentAnimations[INDEX_FRAGMENT_ANIMATION_POP_EXIT]
            )
            replace(fragmentHostId, fragment)
            if (addToBackStack) {
                addToBackStack(backStackName)
            }
        }
    }

    open fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.replaceFragment(fragment, this.fragmentHostId, this.fragmentAnimations)
    }

    fun openStartFragment (fragment: Fragment?) {
        supportFragmentManager.openStartFragment(fragment, fragmentHostId, fragmentAnimations)
    }
}