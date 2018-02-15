package com.artur.sms.DataTransfer

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Artur on 2/7/2018.
 */
@Entity(tableName ="NotificationLists" , indices = arrayOf(Index(unique = true, value = arrayOf("name"))))
class ListDto(
        @PrimaryKey(autoGenerate = true)var id:Int?,
        var name: String,
        var isDefault:Boolean = false) {
    constructor():this(0,"",true)
}