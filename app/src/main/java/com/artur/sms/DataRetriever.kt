package com.artur.sms

import android.content.Context
import android.os.AsyncTask

/**
 * Created by Artur on 2/2/2018.
 */
class DataRetriever( context: Context) : AsyncTask<Unit, Unit, NotificationList>() {
val database = AppDatabase.getDatabase(context);



    override fun doInBackground(vararg p0: Unit?): NotificationList {
        val contacts = database.contactDao().selectAll();

return NotificationList("INFO", contacts.toList());
    }

    override fun onPostExecute(result: NotificationList?) {
        super.onPostExecute(result)

        if(result != null) {
           // onComplete.call(result)
        }
    }

    fun test(): AppDatabase {
        
return database
    }

    fun test(load: Unit) {}

}