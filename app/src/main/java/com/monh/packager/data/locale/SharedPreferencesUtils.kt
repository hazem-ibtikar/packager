package com.monh.packager.data.locale

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.monh.packager.data.remote.auth.User
import com.monh.packager.utils.localization.ARABIC
import com.monh.packager.utils.localization.ENGLISH
import com.monh.packager.utils.localization.LocalLanguage
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

class SharedPreferencesUtils(private val context: Context) {
    private val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    fun putString(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    fun getString(key: String, def: String?): String? {
        return pref.getString(key, def)
    }

    fun putBoolean(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, def: Boolean): Boolean {
        return pref.getBoolean(key, def)
    }

    fun getList(key: String): ArrayList<String> {
        return getString(key, null)?.let {
            Gson().fromJson(it, ArrayList<String>()::class.java)
        } ?: ArrayList()
    }

    fun putList(key: String, value: ArrayList<String>) {
        putString(key, Gson().toJson(value))
    }

    fun addItemToList(key: String, value: String) {
        val arrayList = getList(key)
        if(!arrayList.contains(value)) {
            arrayList.add(value)
            putList(key, arrayList)
        }
    }

    fun removeItemFromList(key: String, value: String) {
        val arrayList = getList(key)
        arrayList.remove(value)
        putList(key, arrayList)
    }


    //*******************************************************************//
    @get:LocalLanguage
    @setparam:LocalLanguage
    var currentLanguage: String
        get() {
            val language = getString(LANGUAGE_KEY, null)
            return when {
                language != null -> when {
                    language == ARABIC -> ARABIC
                    language == ENGLISH -> ENGLISH
                    ARABIC.equals(Locale.getDefault().language, ignoreCase = true) -> ARABIC
                    else -> ENGLISH
                }
                ARABIC.equals(Locale.getDefault().language, ignoreCase = true) -> ARABIC
                else -> ENGLISH
            }
        }
        set(value) {
            putString(LANGUAGE_KEY, value)
        }


    var userLoginResponse: User?
        get() {
            return Gson().fromJson(getString(USER_LOGIN_RESPONSE,null), User::class.java)
        }
        set(value) {
            putString(USER_LOGIN_RESPONSE, Gson().toJson(value))
        }
}

const val LANGUAGE_KEY = "language_key"
const val USER_LOGIN_RESPONSE = "USER_LOGIN_RESPONSE"

