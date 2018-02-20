package com.artur.sms.Domain

import com.artur.sms.DataTransfer.ContactDto
import com.artur.sms.DataTransfer.ListDto
import kotlin.collections.List

/**
 * Created by Artur on 1/27/2018.
 */
class NotificationList(val id: Int?, var name:String, var isDefault : Boolean = false) {
    constructor(listDto: ListDto):this(listDto.id, listDto.name, listDto.isDefault)
    constructor(name:String): this(null, name,false)

    val messageTemplate:String =""
    val members: ArrayList<ContactDto> = ArrayList()
}