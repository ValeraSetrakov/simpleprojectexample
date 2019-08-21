package com.valerasetrakov.simpleexample.data.profile

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.valerasetrakov.base.ModifyDao
import com.valerasetrakov.simpleexample.data.profile.entity.Profile

@Dao
interface ProfileDao : ModifyDao<Profile> {

    @Query("select * from Profile where id = :id")
    fun subscribeToProfileById(id: String): LiveData<Profile>

}