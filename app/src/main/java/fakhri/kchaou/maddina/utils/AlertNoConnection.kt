package fakhri.kchaou.maddina.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import fakhri.kchaou.maddina.R

class AlertNoConnection internal constructor(private val activity: Activity) {


        private lateinit var alertDialog : AlertDialog
        fun startLoadingAlert() {
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            builder.setView(inflater.inflate(R.layout.alert_no_connection, null))



            builder.setCancelable(false)

            alertDialog = builder.create()
            alertDialog.show()
        }

        fun dismissDialog() {
            alertDialog.cancel()

        }

}