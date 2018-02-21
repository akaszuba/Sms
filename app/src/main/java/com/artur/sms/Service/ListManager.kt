package com.artur.sms.Service

import android.content.Context
import com.artur.sms.DataAccess.AppDatabase
import com.artur.sms.DataTransfer.ContactDto
import com.artur.sms.DataTransfer.ContactListDto
import com.artur.sms.DataTransfer.ListDto
import com.artur.sms.Domain.Contact
import com.artur.sms.Domain.ListRoles
import com.artur.sms.Domain.NotificationList
import com.artur.sms.Utility.formatPhone

/**
 * Created by Artur on 2/7/2018.
 */
class ListManager(context: Context) {
    private val contactDao = AppDatabase.getDatabase(context).contactDao()

    fun getActiveLists(): List<NotificationList> {

        val xrefsDtos = contactDao.selectAllXrefs()
        val contacts = contactDao.selectAllContacts().map{dto-> Contact(dto, xrefsDtos.filter { xref->xref.contactId == dto.id }) }

        return contactDao.selectAllLists().map { listDto ->
            NotificationList(listDto, contacts.filter { contact->contact.isListMember(listDto.id!!) })
        }
    }

    fun createNewList(name: String) {
        val dto = ListDto(null, name, false)
        contactDao.insert(dto)
    }

    fun deleteList(listToDelete:NotificationList){

    }

    fun createNewUser(name: String, phoneNumber: String, listId: Int, role: ListRoles){
        var contact = contactDao.selectAllContacts().find { contact-> contact.phoneNumber == phoneNumber.formatPhone() }
        if(contact == null){
            contact = ContactDto(phoneNumber = phoneNumber, name = name)
            val id = contactDao.insert(contact)
            contact.id = id.toInt()
        }

        val xref = ContactListDto(listId,contact.id!!,role);
        contactDao.insert(xref)
    }

    fun removeUserFromList(contactId:Int, listId:Int){
        var xrefToDelete =  contactDao.selectAllXrefs().find { xref-> xref.contactId == contactId && xref.listId == listId }
        if (xrefToDelete != null){ contactDao.delete(xrefToDelete)}

        //delete user if it is not assigned to any other list
        if(!contactDao.selectAllXrefs().any{xref-> xref.contactId == contactId}){
            val contactToDelete = contactDao.selectAllContacts().find { contact-> contact.id == contactId }
            if(contactToDelete != null){
                contactDao.delete(contactToDelete)
            }
        }
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