package com.artur.sms

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by Artur on 2/2/2018.
 */
@Dao
interface ContactDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addContact( dto:ContactDto)

    @Query("SELECT * FROM Contacts")
    fun selectAll(): Array<ContactDto>
}

