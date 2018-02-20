package com.artur.sms.View

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.text.InputType
import android.widget.*
import com.artur.sms.Domain.ListRoles

/**
 * Created by Artur on 2/15/2018.
 */
class NameAndPhonePrompt(val okClicked: (String, String) -> Boolean,
                         val context: Context) {
    val builder = AlertDialog.Builder(context)
        val txtName = EditText(context)
    val txtPhone = EditText(context)
    val spinner = Spinner(context)
    var alertDialog : Dialog
    init {
        val lay = LinearLayout(context)
        lay.orientation = LinearLayout.VERTICAL
        txtPhone.inputType = InputType.TYPE_CLASS_PHONE
        spinner.adapter = ArrayAdapter<ListRoles>(context,android.R.layout.simple_spinner_dropdown_item,ListRoles.values())
        lay.addView(txtName)
        lay.addView(txtPhone)
        lay.addView(spinner)
        builder.setMessage("Please enter name")
                .setTitle("Name?")
               .setView(lay)
                .setPositiveButton("Create", { dialog, id ->
                    if (okClicked?.invoke(txtName.editableText.toString(), txtPhone.editableText.toString()) == true) {

                    }
                })
                .setNegativeButton("Cancel", { dialog, id ->

                })
        alertDialog =builder.create()
    }

    fun show() {
        alertDialog.show()
    }
}