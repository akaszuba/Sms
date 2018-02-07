package com.artur.sms

import android.content.Context
import android.widget.Toast

/**
 * Created by Artur on 2/7/2018.
 */
class SmsHandler(private var context: Context){
    private var contactDao = AppDatabase.getDatabase(context)

    fun handleMessage(message: SmsMessage){
        Toast.makeText(context,message.text,Toast.LENGTH_LONG).show()
    }
}