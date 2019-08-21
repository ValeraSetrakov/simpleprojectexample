package com.valerasetrakov.simpleexample.screen.main.profile

import com.valerasetrakov.simpleexample.data.profile.ProfileDao
import com.valerasetrakov.simpleexample.data.profile.ProfileRepository
import javax.inject.Inject

class ProfileFragmentRepository @Inject constructor(private val profileRepository: ProfileRepository)
    : ProfileDao by profileRepository {
    suspend fun requestProfile(id: String) = profileRepository.requestProfile(id)
}