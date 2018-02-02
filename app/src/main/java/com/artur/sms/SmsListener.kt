package com.artur.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.widget.Toast
import android.arch.persistence.room.Room



class SmsListener : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent?.action) {

            val db = Room.databaseBuilder(context!!,
                    AppDatabase::class.java, "database-name").build()

            for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                val messageBody = smsMessage.messageBody
                Toast.makeText(context,messageBody,Toast.LENGTH_LONG).show();
            }
        }
    }
}