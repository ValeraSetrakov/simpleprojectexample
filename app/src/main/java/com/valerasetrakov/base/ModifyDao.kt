package com.valerasetrakov.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/** Parent of Data access object that execute insert, update of delete operations with sql database */
interface ModifyDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: T)
    @Delete
    suspend fun delete(item: T)
    @Update
    suspend fun update(item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<T>)
    @Delete
    suspend fun delete(item: List<T>)
    @Update
    suspend fun update(item: List<T>)

}