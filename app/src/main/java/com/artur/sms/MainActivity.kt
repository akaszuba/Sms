package com.artur.sms

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.v4.app.ActivityCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(!isSmsPermissionGranted()) {
            requestReadAndSendSmsPermission()
        }
    }

    fun smsRecieved(text: String){
        val btn = findViewById<View>(R.id.button) as Button
        btn.text = "elo"
    }

    fun buttonClicked(view: View){
        val  btn  = view as Button;
        btn.setText("Button Clicked");
    }

    fun isSmsPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
    }

    fun requestReadAndSendSmsPermission() {

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS),123)
    }
}
