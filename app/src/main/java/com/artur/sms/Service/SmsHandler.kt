package com.artur.sms.Service

import android.content.Context
import android.widget.Toast
import com.artur.sms.DataAccess.AppDatabase
import com.artur.sms.Domain.SmsMessage

/**
 * Created by Artur on 2/7/2018.
 */
class SmsHandler(private var context: Context){
    private var contactDao = AppDatabase.getDatabase(context)

    fun handleMessage(message: SmsMessage){
        Toast.makeText(context,message.text,Toast.LENGTH_LONG).show()
    }
}