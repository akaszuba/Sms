package com.artur.sms.DataTransfer

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Artur on 1/27/2018.
 */
@Entity(tableName = "Contacts" , indices = arrayOf(Index(unique = true, value = "phoneNumber")))
class ContactDto(
        @PrimaryKey(autoGenerate = true)
        var id:Int? = null,
        var phoneNumber:String,
        var name :String) {
 constructor():this(0,"","")
}