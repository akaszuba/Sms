package com.artur.sms.Service

import android.content.Context
import com.artur.sms.DataAccess.AppDatabase
import com.artur.sms.DataTransfer.ListDto
import com.artur.sms.Domain.NotificationList
import com.artur.sms.Utility.formatPhone

/**
 * Created by Artur on 2/7/2018.
 */
class ListManager(context: Context) {
    private val contactDao = AppDatabase.getDatabase(context).contactDao()

    fun getActiveLists(): List<NotificationList> {

        return contactDao.selectAllLists().map { listDto ->
            NotificationList(listDto)
        }
    }

    fun createNewList(name: String): NotificationList {
        val dto = ListDto(null, name, false)
        contactDao.insert(dto)

        return NotificationList(dto)
    }

    fun createNewUser(name: String, phoneNumber: String, listId: Int){
        val existingContact = contactDao.selectAll().find { contact-> contact.phoneNumber == phoneNumber.formatPhone() }


    }


    companion object {
        private var localInstance: ListManager? = null
        fun getInstance(context: Context): ListManager {
            if (localInstance == null) {
                localInstance = ListManager(context)
            }
            return localInstance!!
        }
    }
}