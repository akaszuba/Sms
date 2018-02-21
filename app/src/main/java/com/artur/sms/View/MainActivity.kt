package com.artur.sms.View

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.v4.app.ActivityCompat
import android.widget.*
import com.artur.sms.Domain.ListRoles
import com.artur.sms.Domain.NotificationList
import com.artur.sms.R
import com.artur.sms.Service.ListManager
import com.artur.sms.Service.SmsSender
import com.artur.sms.Utility.AsyncTask


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isSmsPermissionGranted()) {
            requestReadAndSendSmsPermission()
        }


        //init controls
        val spinner = findViewById<Spinner>(R.id.selectList)
        spinner.onItemSelectedListener = this
        //load data
        refreshData()
    }

    fun refreshData() {
        AsyncTask({ ListManager.getInstance(this).getActiveLists() }, { result ->
            reloadSpinner(result)
        }).execute()
    }


    fun reloadSpinner(lists: List<NotificationList>, selectedListId: Int? = null) {
        val spinner = findViewById<Spinner>(R.id.selectList)
        spinner.adapter = ArrayAdapter<NotificationList>(this, android.R.layout.simple_spinner_dropdown_item, lists)
    }

    fun loadDataToView(list: NotificationList?) {
        val txtName = findViewById<TextView>(R.id.txtListName)
        txtName.text = list?.name
    }


    fun buttonClicked(view: View) {

        val listToDelete = findViewById<Spinner>(R.id.selectList).selectedItem as NotificationList
        listToDelete.name = "jjjjj"

        val kkk = listToDelete.toString()
    }

    fun removeCurrentListClicked(view: View) {
        val listToDelete = findViewById<Spinner>(R.id.selectList).selectedItem as NotificationList
        AsyncTask({
            ListManager.getInstance(this).deleteList(listToDelete)
        }, { refreshData() }).execute()
    }

    fun addNewListClicked(view: View) {
        val prompt = NamePrompt({ name ->
            if (name != "") {
                AsyncTask({
                    ListManager.getInstance(this).createNewList(name)
                }, { refreshData() }).execute()
                true
            } else false
        }, this)

        prompt.show()
    }

    fun addContactClicked(view: View) {
        val prompt = NameAndPhonePrompt({ name, phone ->
            if (name != "") {
                AsyncTask({
                    ListManager.getInstance(this)

                            .createNewUser(name, phone, 1, ListRoles.Member)
                }, { refreshData() }).execute()
                true
            } else false
        }, this)

        prompt.show()
    }

    //onItemSelectedListener
    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        loadDataToView(parent?.selectedItem as NotificationList?)
    }

    //permissions
    fun isSmsPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
    }

    fun requestReadAndSendSmsPermission() {

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_STATE), 123)
    }
}


