package com.monh.packager.utils.localization

import androidx.annotation.StringDef


@Retention(AnnotationRetention.SOURCE)
@StringDef(ARABIC, ENGLISH)
annotation class LocalLanguage

const val ARABIC = "ar"
const val ENGLISH = "en"




