package com.valerasetrakov.simpleexample.screen.main.profile

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.valerasetrakov.base.invokeAsyncOperation
import com.valerasetrakov.base.lazyMap
import com.valerasetrakov.base.setValueIfNotSame
import com.valerasetrakov.simpleexample.screen.base.ProjectFragmentViewModel
import javax.inject.Inject

class ProfileFragmentViewModel @Inject constructor(application: Application, private val repository: ProfileFragmentRepository)
    : ProjectFragmentViewModel(application) {

    private val profilesMap = lazyMap<Parameters, LiveData<ProfileViewModel>> {
        val id = it.id
        return@lazyMap Transformations.map(repository.subscribeToProfileById(id)) { profile ->
            ProfileViewModel(
                avatarReference = profile?.avatar ?: "",
                name = profile?.name ?: "",
                age = "${profile?.age ?: 0}",
                study = profile?.study ?: ""
            )
        }
    }

    private val _profile = MutableLiveData<ProfileViewModel>()
    val profile: LiveData<ProfileViewModel> = _profile

    fun setProfile(profileViewModel: ProfileViewModel) {
        _profile.setValueIfNotSame(profileViewModel)
    }


    fun subscribeToProfile(id: String): LiveData<ProfileViewModel> {
        invokeAsyncOperation { repository.requestProfile(id) }
        return profilesMap.getValue(Parameters(id))
    }


    data class Parameters (
        val id: String
    )

    data class ProfileViewModel(
        val avatarReference: String,
        val name: CharSequence,
        val age: CharSequence,
        val study: CharSequence
    )
}