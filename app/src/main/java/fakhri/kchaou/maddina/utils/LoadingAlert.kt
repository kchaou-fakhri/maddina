package fakhri.kchaou.maddina.utils

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import fakhri.kchaou.maddina.R

class LoadingAlert internal constructor(private val activity: Activity) {
    private lateinit var alertDialog : AlertDialog
    fun startLoadingAlert() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_alert, null))
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog.show()
    }

    fun dismissDialog() {
        alertDialog!!.dismiss()
    }
}