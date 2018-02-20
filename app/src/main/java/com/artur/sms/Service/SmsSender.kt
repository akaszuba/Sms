package com.artur.sms.Service

import android.content.Context
import android.telephony.SmsManager
import com.artur.sms.DataTransfer.ContactDto
import com.artur.sms.Domain.NotificationList
import com.artur.sms.Domain.PlaceHolders
import java.util.*

/**
 * Created by AKASZUBA on 2/20/2018.
 */
class SmsSender {
    fun sendTextMessage(sender: ContactDto, list: NotificationList, text: String, allowMultipleMessage: Boolean = false) {

        var message = list.messageTemplate
                .replace(PlaceHolders.ListName.id, list.name)
                .replace(PlaceHolders.SenderName.id, sender.name)
                .replace(PlaceHolders.SenderPhoneNumber.id, sender.phoneNumber)
                .replace(PlaceHolders.CurrentTime.id, Calendar.getInstance().time.toString())
                .replace(PlaceHolders.Text.id, text)

        val splittedMessage = SmsManager.getDefault().divideMessage(message)

        list.members.forEach({ member ->
            if (allowMultipleMessage)
                SmsManager.getDefault().sendMultipartTextMessage(member.phoneNumber, null, splittedMessage, null, null)
            else SmsManager.getDefault().sendTextMessage(member.phoneNumber, null, splittedMessage.first(), null, null)
        })
    }
}