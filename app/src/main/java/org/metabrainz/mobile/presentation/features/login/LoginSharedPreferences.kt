package org.metabrainz.mobile.presentation.features.login

import android.content.Context
import android.content.SharedPreferences
import org.metabrainz.mobile.App
import org.metabrainz.mobile.data.sources.api.entities.AccessToken
import org.metabrainz.mobile.data.sources.api.entities.userdata.UserInfo

object LoginSharedPreferences {
    const val ACCESS_TOKEN = "access_token"
    const val REFRESH_TOKEN = "refresh_token"
    private const val USERNAME = "username"
    const val STATUS_LOGGED_IN = 1
    const val STATUS_LOGGED_OUT = 0
    private const val SHARED_PREF_FILE = "shared_pref_file"
    val preferences: SharedPreferences
        get() = App.context!!.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)

    fun saveOAuthToken(token: AccessToken) {
        val editor = preferences.edit()
        editor.putString(ACCESS_TOKEN, token.accessToken)
        editor.putString(REFRESH_TOKEN, token.refreshToken)
        editor.apply()
    }

    fun saveUserInfo(userInfo: UserInfo) {
        val editor = preferences.edit()
        editor.putString(USERNAME, userInfo.username)
        editor.apply()
    }

    fun logoutUser() {
        val editor = preferences.edit()
        editor.remove(ACCESS_TOKEN)
        editor.remove(REFRESH_TOKEN)
        editor.remove(USERNAME)
        editor.apply()
    }

    @JvmStatic
    val loginStatus: Int
        get() {
            val accessToken = accessToken
            val username = username
            return if (accessToken!!.isNotEmpty() && username!!.isNotEmpty()) STATUS_LOGGED_IN else STATUS_LOGGED_OUT
        }
    val accessToken: String?
        get() = preferences.getString(ACCESS_TOKEN, "")
    @JvmStatic
    val username: String?
        get() = preferences.getString(USERNAME, "")
    val refreshToken: String?
        get() = preferences.getString(REFRESH_TOKEN, "")
}