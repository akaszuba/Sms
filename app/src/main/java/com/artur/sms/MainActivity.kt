package com.artur.sms

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.v4.app.ActivityCompat
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    var db: AppDatabase? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (!isSmsPermissionGranted()) {
            requestReadAndSendSmsPermission()
        }

        db = AppDatabase.getDatabase(applicationContext);
    }

    fun refreshData(array: Array<ContactDto>) {
        if (db != null) {
            var list = findViewById<TextView>(R.id.textView)
            array.forEach { item ->
                list.append("Name: ${item.name}, Phone: ${item.phoneNumber}.\r\n")
            }
        }
    }

    fun buttonClicked(view: View) {
        if (db != null) {
            AsyncTask({ db!!.contactDao().selectAll() }, { res -> refreshData(res) }).execute()

            val btn = view as Button;
            btn.setText("Button Clicked");
        }
    }

    fun addContactClicked(view: View){
        if (db != null) {
            AsyncTask({ db!!.contactDao().addContact(ContactDto("345","contact1"))
            }).execute()
        }
    }

    fun isSmsPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
    }

    fun requestReadAndSendSmsPermission() {

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS), 123)
    }
}
