package com.valerasetrakov.simpleexample.data.profile

import com.valerasetrakov.simpleexample.data.api.ContentApi
import com.valerasetrakov.simpleexample.data.profile.entity.RequestProfile
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileDao: ProfileDao,
                                            private val contentApi: ContentApi): ProfileDao by profileDao, ContentApi by contentApi {

    suspend fun requestProfile(id: String) {
        val responseProfile = contentApi.requestProfile(RequestProfile(id))
        val profile = ProfileMapper.mapToProfile(responseProfile)
        profileDao.insert(profile)
    }
}