package com.artur.sms

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Artur on 1/27/2018.
 */
@Entity(tableName = "Contacts")
class ContactDto(@PrimaryKey var phoneNumber:String, var name :String) {
constructor():this("","")
}