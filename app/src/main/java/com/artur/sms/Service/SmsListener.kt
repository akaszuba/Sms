package com.artur.sms.Service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.artur.sms.Domain.SmsMessage

class SmsListener : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent?.action) {
            if (context != null) {
                var messages = arrayListOf<SmsMessage>()
                for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent).sortedBy { m -> "${m.originatingAddress} - ${m.timestampMillis}" }) {
                    var messageForCurrentSender = messages.find { m -> m.sender == smsMessage.originatingAddress }
                    if (messageForCurrentSender == null) {
                        messageForCurrentSender = SmsMessage(smsMessage.originatingAddress)
                        messages.add(messageForCurrentSender)
                    }
                    messageForCurrentSender.text += smsMessage.displayMessageBody
                }
                messages.forEach({ m -> smsHandlerInstance(context).handleMessage(m) })
            }
        }
    }

    companion object {
        private var smsHandler: SmsHandler? = null
        fun smsHandlerInstance(context: Context): SmsHandler {
            if (smsHandler == null) {
                smsHandler = SmsHandler(context)
            }
            return smsHandler!!
        }
    }
}