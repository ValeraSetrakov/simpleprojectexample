package com.valerasetrakov.simpleexample.data.profile

import com.valerasetrakov.simpleexample.data.profile.entity.Profile
import com.valerasetrakov.simpleexample.data.profile.entity.ResponseProfile

object ProfileMapper {
    fun mapToProfile(responseProfile: ResponseProfile) = Profile(
        id = responseProfile.id,
        name = responseProfile.name,
        age = responseProfile.age,
        avatar =  responseProfile.avatar,
        study = responseProfile.study
    )
}