package br.well.tembici.common.view.base

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity

abstract class BaseAppProvider(open val activity: AppCompatActivity) {
    val context by lazy { activity as Context }
    val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }
}