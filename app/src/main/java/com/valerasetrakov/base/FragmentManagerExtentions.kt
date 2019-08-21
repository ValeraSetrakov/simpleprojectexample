package com.valerasetrakov.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.transaction

fun FragmentManager.replaceFragment(fragment: Fragment, fragmentHostId: Int, fragmentAnimations: Array<Int>? = null) {
    val backStackName = fragment::class.java.simpleName
    val tag = backStackName
    val manager = this
    val fragmentByTag = manager.findFragmentByTag(tag)
    val currentFragment = manager.findFragmentById(fragmentHostId)
    if (currentFragment?.equals(fragmentByTag) == true)
        return
    if (fragmentByTag == null) {
        manager.transaction {
            if (fragmentAnimations != null) {
                setCustomAnimations(
                    fragmentAnimations[0],
                    fragmentAnimations[1],
                    fragmentAnimations[2],
                    fragmentAnimations[3]
                )
            }
            currentFragment?.let { detach(it) }
            add(fragmentHostId, fragment, tag)
            addToBackStack(backStackName)
        }
    } else {
        manager.transaction {
            if (fragmentAnimations != null) {
                setCustomAnimations(
                    fragmentAnimations[0],
                    fragmentAnimations[1],
                    fragmentAnimations[2],
                    fragmentAnimations[3]
                )
            }
            currentFragment?.let { detach(it) }
            attach(fragmentByTag)
            addToBackStack(backStackName)
        }
    }
}

fun FragmentManager.openStartFragment (fragment: Fragment?, fragmentHostId: Int, fragmentAnimations: Array<Int>? = null) {
    fragment?.let {
        val tag = fragment::class.java.simpleName
        val manager = this
        val currentFragment = manager.findFragmentById(fragmentHostId)
        if (currentFragment == fragment)
            return
        transaction {
            if (fragmentAnimations != null) {
                setCustomAnimations(
                    fragmentAnimations[0],
                    fragmentAnimations[1],
                    fragmentAnimations[2],
                    fragmentAnimations[3]
                )
            }
            if (currentFragment != null)
                remove(currentFragment)
            add(fragmentHostId, fragment, tag)
        }
    }
}