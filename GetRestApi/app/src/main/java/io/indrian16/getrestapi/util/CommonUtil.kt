package io.indrian16.getrestapi.util

import android.content.Context
import android.os.Build
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.TextView
import io.indrian16.getrestapi.R

object CommonUtil {

    fun showOnlineStatus(view: View, context: Context): Snackbar {

        val snackbar = Snackbar.make(view, AppConstant.ONLINE, Snackbar.LENGTH_LONG)
        val snackView = snackbar.view
        val tvSnackbar: TextView = snackView.findViewById(android.support.design.R.id.snackbar_text)

        // Edit Snakbar
        snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        tvSnackbar.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

            tvSnackbar.textAlignment = View.TEXT_ALIGNMENT_CENTER

        } else {

            tvSnackbar.gravity = Gravity.CENTER_HORIZONTAL
        }

        return snackbar
    }

    fun showOfflineStatus(view: View, context: Context): Snackbar {

        val snackbar = Snackbar.make(view, AppConstant.OFFLINE, Snackbar.LENGTH_LONG)
        val snackView = snackbar.view
        val tvSnackbar: TextView = snackView.findViewById(android.support.design.R.id.snackbar_text)

        // Edit Snakbar
        snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
        tvSnackbar.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

            tvSnackbar.textAlignment = View.TEXT_ALIGNMENT_CENTER

        } else {

            tvSnackbar.gravity = Gravity.CENTER_HORIZONTAL
        }

        return snackbar
    }
}