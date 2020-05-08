package com.monh.packager.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.lifecycle.MutableLiveData


fun Float.toPixels(context: Context):Int{
    var r: Resources = context.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        r.displayMetrics
    ).toInt()
}

fun String.toBearerToken():String{
    return "Bearer $this"
}

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
