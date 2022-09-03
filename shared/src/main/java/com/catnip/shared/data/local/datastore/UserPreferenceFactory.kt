package com.catnip.shared.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class UserPreferenceFactory(private val appContext: Context) {
    fun create(): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCE) }
        )
    }

    companion object {
        const val USER_PREFERENCE = "user_preferences"
    }
}

object UserPreferenceKey {
    val userToken = stringPreferencesKey(PreferenceKey.PREF_USER_TOKEN)
    val isUserLoggedIn = booleanPreferencesKey(PreferenceKey.PREF_IS_USER_LOGGED_IN)
    val userObject = stringPreferencesKey(PreferenceKey.PREF_USER_OBJECT)
}

object PreferenceKey {
    const val PREF_USER_TOKEN = "PREF_USER_TOKEN"
    const val PREF_IS_USER_LOGGED_IN = "PREF_IS_USER_LOGGED_IN"
    const val PREF_USER_OBJECT = "PREF_USER_OBJECT"
}