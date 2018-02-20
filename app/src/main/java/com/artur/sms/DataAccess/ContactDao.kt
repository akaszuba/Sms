package com.artur.sms.DataAccess

import android.arch.persistence.room.*
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
    fun selectAllContacts(): Array<ContactDto>

    @Query("SELECT * FROM ContactListXrefs")
    fun selectAllXrefs(): Array<ContactListDto>

    @Delete()
    fun delete(dto:ContactDto)
    @Delete()
    fun delete(dto:ListDto)
    @Delete()
    fun delete(dto:ContactListDto)

}

