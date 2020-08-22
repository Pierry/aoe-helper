package com.gjithub.pierry.aoehelper.core.common

import android.content.Context
import android.content.SharedPreferences

class SharedPref(val context: Context) {

  var sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedPrefAOE", Context.MODE_PRIVATE)

  fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)

  fun getString(key: String) = sharedPreferences.getString(key, "")

  fun getDouble(key: String): Double {
    val res = sharedPreferences.getFloat(key, 0f)
    return res.toDouble()
  }

  fun getInt(key: String) = sharedPreferences.getInt(key, 0)

  fun getLong(key: String) = sharedPreferences.getLong(key, 0L)

  fun put(key: String, text: Boolean) = sharedPreferences.edit().putBoolean(key, text).apply()

  fun put(key: String, text: String) = sharedPreferences.edit().putString(key, text).apply()

  fun put(key: String, text: Double) = sharedPreferences.edit().putFloat(key, text.toFloat()).apply()

  fun put(key: String, text: Int) = sharedPreferences.edit().putInt(key, text).apply()

  fun put(key: String, text: Long) = sharedPreferences.edit().putLong(key, text).apply()

  fun clear() = sharedPreferences.edit().clear().commit()

  companion object {
    const val NAME = "name"
    const val RATING = "rating"
    const val STEAM_ID = "steamId"
  }
}