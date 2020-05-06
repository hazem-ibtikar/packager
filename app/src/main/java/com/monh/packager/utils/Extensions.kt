package com.monh.packager.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue


fun Float.toPixels(context: Context):Int{
    var r: Resources = context.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        r.displayMetrics
    ).toInt()
}