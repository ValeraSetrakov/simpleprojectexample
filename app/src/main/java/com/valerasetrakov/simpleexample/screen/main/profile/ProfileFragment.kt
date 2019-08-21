package com.valerasetrakov.simpleexample.screen.main.profile

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.valerasetrakov.simpleexample.R
import com.valerasetrakov.simpleexample.databinding.FragmentProfileBinding
import com.valerasetrakov.simpleexample.screen.base.ProjectFragment
import com.valerasetrakov.simpleexample.screen.main.MainActivityFragment
import com.valerasetrakov.simpleexample.screen.main.MainActivityViewModel

class ProfileFragment: MainActivityFragment<ProfileFragmentViewModel, FragmentProfileBinding>() {

    companion object {
        private const val PROFILE_ID_KEY = "PROFILE_ID_KEY"
        fun create(profileId: String) = ProfileFragment().apply {
            arguments = bundleOf(PROFILE_ID_KEY to profileId)
        }
    }

    override val layoutId: Int = R.layout.fragment_profile
    override val viewModelType: Class<ProfileFragmentViewModel> = ProfileFragmentViewModel::class.java
    override val toolbarTitleId: Int = R.string.profile

    private lateinit var profileId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileId = arguments?.getString(PROFILE_ID_KEY) ?: throw RuntimeException("Need profile id!")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.subscribeToProfile(profileId).observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            viewmodel.setProfile(it)
        })
    }

}