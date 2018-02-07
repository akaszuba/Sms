package com.artur.sms
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.TypeConverters


/**
 * Created by Artur on 1/27/2018.
 */
@Entity(tableName = "ContactListXrefs", primaryKeys = arrayOf("listName","phoneNumber"),
        foreignKeys = arrayOf(ForeignKey(entity = ContactDto::class, parentColumns = arrayOf("phoneNumber"), childColumns = arrayOf("phoneNumber"))))
class ContactListDto( var listName :String, var phoneNumber:String,  var role: ListRoles = ListRoles.Member){

    constructor():this("","")
}

