package com.artur.sms.DataAccess

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.artur.sms.DataTransfer.ContactDto
import com.artur.sms.DataTransfer.ContactListDto
import com.artur.sms.DataTransfer.ListDto

/**
 * Created by Artur on 2/2/2018.
 */
@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dto: ContactDto): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dto: ListDto)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dto: ContactListDto)

    @Query("SELECT * FROM NotificationLists")
    fun selectAllLists(): Array<ListDto>

    @Query("SELECT * FROM Contacts")
    fun selectAll(): Array<ContactDto>


}

