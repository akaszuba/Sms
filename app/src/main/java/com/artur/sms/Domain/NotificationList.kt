package com.artur.sms.Domain

import com.artur.sms.DataTransfer.ContactDto
import com.artur.sms.DataTransfer.ListDto
import kotlin.collections.List

/**
 * Created by Artur on 1/27/2018.
 */
class NotificationList(private val listDto: ListDto, val members:List<Contact> ) {

    var messageTemplate
            get() = listDto.messageTemplate
        set(value) {listDto.messageTemplate =value}

    var name
        get() = listDto.name
        set(value) {listDto.name =value}

    override fun toString(): String {
        return listDto.name;
    }
}