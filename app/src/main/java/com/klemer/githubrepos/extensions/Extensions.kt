package com.klemer.githubrepos.extensions

import android.content.res.Resources
import android.graphics.Rect
import androidx.fragment.app.DialogFragment

fun DialogFragment.setWidthPercent(widthPercentage: Int, heightPercentage: Int) {
    val wPercent = widthPercentage.toFloat() / 100
    val hPercent = heightPercentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
    val percentWidth = rect.width() * wPercent
    val percentHeight = rect.width() * hPercent

    dialog?.window?.setLayout(percentWidth.toInt(), percentHeight.toInt())
}