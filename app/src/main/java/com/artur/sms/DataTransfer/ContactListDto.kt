package com.artur.sms.DataTransfer

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import com.artur.sms.Domain.ListRoles


/**
 * Created by Artur on 1/27/2018.
 */
@Entity(tableName = "ContactListXrefs", primaryKeys = arrayOf("listName", "phoneNumber"),
        foreignKeys = arrayOf(
                ForeignKey(entity = ContactDto::class, parentColumns = arrayOf("phoneNumber"), childColumns = arrayOf("phoneNumber")),
                ForeignKey(entity = ListDto::class, parentColumns = arrayOf("name"), childColumns = arrayOf("listName"))))
class ContactListDto(var listName: String, var phoneNumber: String, var role: ListRoles = ListRoles.Member) {
    constructor() : this("", "")
}

