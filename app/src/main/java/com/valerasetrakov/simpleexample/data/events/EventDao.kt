package com.valerasetrakov.simpleexample.data.events

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.valerasetrakov.base.ModifyDao
import com.valerasetrakov.simpleexample.data.events.entity.Event

@Dao
interface EventDao: ModifyDao<Event> {

    @Query("select * from Event where id = :id")
    fun subsctibeToEventById(id: String): LiveData<Event>

    @Query("select * from Event")
    fun subsctibeToEvents(): LiveData<List<Event>>
}