package com.kursatmemis.mycontacts.util

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import com.kursatmemis.mycontacts.R

class AlertDialogUtil(
    private val context: Context,
    private val title: String = "",
    private val message: String = "",
    private val positiveButtonOnClickListener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { _, _ ->

    },
    private val negativeButtonOnClickListener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { _, _ ->

    }
) {

    fun createAlertDialog(): AlertDialog.Builder {
        val alertDialog = AlertDialog.Builder(ContextThemeWrapper(context, R.style.Theme_MyContacts))
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Yes", positiveButtonOnClickListener)
        alertDialog.setNegativeButton("No", negativeButtonOnClickListener)
        return alertDialog
    }

}