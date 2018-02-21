package com.artur.sms.Domain

import com.artur.sms.DataTransfer.ContactDto
import com.artur.sms.DataTransfer.ContactListDto

/**
 * Created by AKASZUBA on 2/21/2018.
 */
class Contact(private val dto: ContactDto, private var xrefs:List<ContactListDto>) {

    val phoneNumber get() = dto.phoneNumber

    fun isListMember(listId:Int):Boolean{
        return xrefs.any({xref->xref.listId == listId})
    }
}