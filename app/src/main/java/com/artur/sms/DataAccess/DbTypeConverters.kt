package com.artur.sms.DataAccess

import android.arch.persistence.room.TypeConverter
import com.artur.sms.Domain.ListRoles

/**
 * Created by Artur on 2/7/2018.
 */
class DbTypeConverters{
    @TypeConverter
    fun toInteger(role: ListRoles): Int {
        return role.id
    }

    @TypeConverter
    fun toRole(roleId: Int): ListRoles {
        return ListRoles.values().first { r->r.id == roleId }
    }
}