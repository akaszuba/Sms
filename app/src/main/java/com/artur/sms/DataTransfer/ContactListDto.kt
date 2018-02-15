package com.artur.sms.DataTransfer

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import com.artur.sms.Domain.ListRoles


/**
 * Created by Artur on 1/27/2018.
 */
@Entity(tableName = "ContactListXrefs", primaryKeys = arrayOf("listId", "contactId"),
        foreignKeys = arrayOf(
                ForeignKey(entity = ContactDto::class, parentColumns = arrayOf("id"), childColumns = arrayOf("contactId")),
                ForeignKey(entity = ListDto::class, parentColumns = arrayOf("id"), childColumns = arrayOf("listId"))))
class ContactListDto(var listId: Int, var contactId: Int, var role: ListRoles = ListRoles.Member) {
    constructor() : this(0, 0)
}

