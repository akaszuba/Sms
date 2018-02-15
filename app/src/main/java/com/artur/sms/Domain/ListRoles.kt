package com.artur.sms.Domain

import android.arch.persistence.room.TypeConverter

/**
 * Created by Artur on 2/7/2018.
 */
enum class ListRoles constructor(val id:Int){
    Owner(1) , Member(2)
}

