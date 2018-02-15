package com.artur.sms.View

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.v4.app.ActivityCompat
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.artur.sms.DataAccess.AppDatabase
import com.artur.sms.DataTransfer.ContactDto
import com.artur.sms.R
import com.artur.sms.Service.ListManager
import com.artur.sms.Utility.AsyncTask
import com.artur.sms.Utility.formatPhone


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isSmsPermissionGranted()) {
            requestReadAndSendSmsPermission()
        }

val jj = "+48 09 98".formatPhone();
    }

    fun refreshData() {
        var list = findViewById<TextView>(R.id.textView)
        AsyncTask({ ListManager.getInstance(this).getActiveLists() }, { result ->
            result.forEach { item ->
                list.append("Name: ${item.name}, Phone: ${item.id}.\r\n")
            }
        }).execute()
    }

    fun buttonClicked(view: View) {

        var dialogView = LinearLayout(this)
        dialogView.addView(EditText(this))
        findViewById<LinearLayout>(R.id.linearLayout).addView(dialogView)
    }

    fun addNewListClicked(view: View){
        val prompt =NamePrompt({name->
            if(name != ""){
                AsyncTask({ ListManager.getInstance(this)
                        .createNewList(name)
                }, { refreshData() }).execute()
                true}
            else false
        },this)

        prompt.show()
    }

    fun addContactClicked(view: View) {
        val prompt =NamePrompt({name->
            if(name != ""){
            AsyncTask({ ListManager.getInstance(this)

                            .createNewList(name)
                    }, { refreshData() }).execute()
            true}
            else false
        },this)

        prompt.show()
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


