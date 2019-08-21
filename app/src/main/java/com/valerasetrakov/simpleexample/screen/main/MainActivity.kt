package com.valerasetrakov.simpleexample.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.valerasetrakov.base.startActivityClearStack
import com.valerasetrakov.simpleexample.R
import com.valerasetrakov.simpleexample.databinding.ActivityMainBinding
import com.valerasetrakov.simpleexample.screen.base.ProjectActivity
import com.valerasetrakov.simpleexample.screen.main.events.list.EventsFragment
import com.valerasetrakov.simpleexample.screen.main.profile.ProfileFragment

class MainActivity : ProjectActivity<MainActivityViewModel, ActivityMainBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivityClearStack(intent)
        }
    }

    override val layoutId: Int = R.layout.activity_main
    override val viewModelType: Class<MainActivityViewModel> = MainActivityViewModel::class.java
    override val fragmentHostId: Int
        get() = binding.fragmentContainerView.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        viewmodel.toolbarTitle.observe(this, Observer {
            supportActionBar?.title = it ?: ""
        })
        if (savedInstanceState == null) {
            replaceFragment(ProfileFragment.create("Some id"))
            binding.bottomNavigation.selectedItemId = R.id.menu_item_profile
        }
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_item_schedule -> {
                    replaceFragment(EventsFragment.create())
                    true
                }
                R.id.menu_item_profile -> {
                    replaceFragment(ProfileFragment.create("Some id"))
                    true
                }
                else -> false
            }
        }
    }
}
