package com.artur.sms.View

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.widget.EditText

/**
 * Created by Artur on 2/7/2018.
 */
class NamePrompt(val okClicked: (String) -> Boolean, val context: Context) {
    val builder = AlertDialog.Builder(context)
    val textEdit = EditText(context)
    var alertDialog :Dialog
    init {
        builder.setMessage("Please enter name")
                .setTitle("Name?")
                .setView(textEdit)
                .setPositiveButton("Create", { dialog, id ->
                    if (okClicked?.invoke(textEdit.editableText.toString()) == true) {

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