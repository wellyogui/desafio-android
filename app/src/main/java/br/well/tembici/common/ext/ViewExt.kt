package br.well.tembici.common.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created by well_ on 05/12/2019 for tembici-challenge.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showSnackBar(message: String, duration: Int, actionText: String, actionAction: () -> Unit) {
    Snackbar.make(this, message, duration)
        .setAction(actionText) { actionAction() }
        .show()
}
