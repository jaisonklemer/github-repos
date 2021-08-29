package com.klemer.githubrepos.extensions

import android.content.res.Resources
import android.graphics.Rect
import androidx.fragment.app.DialogFragment
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.ln
import kotlin.math.pow

fun DialogFragment.setWidthPercent(widthPercentage: Int, heightPercentage: Int) {
    val wPercent = widthPercentage.toFloat() / 100
    val hPercent = heightPercentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
    val percentWidth = rect.width() * wPercent
    val percentHeight = rect.width() * hPercent

    dialog?.window?.setLayout(percentWidth.toInt(), percentHeight.toInt())
}

fun Int.formatMin(): String {
    val suffixChars = "KMGTPE"
    val formatter = DecimalFormat("###.#")
    formatter.roundingMode = RoundingMode.DOWN

    return if (this < 1000.0) formatter.format(this)
    else {
        val exp = (ln(this.toDouble()) / ln(1000.0)).toInt()
        formatter.format(this / 1000.0.pow(exp.toDouble())) + suffixChars[exp - 1]
    }
}