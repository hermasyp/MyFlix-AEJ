package com.catnip.shared.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.catnip.shared.data.model.response.UserResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface UserPreferenceDataSource {
    suspend fun clearData()
    suspend fun getUserToken(): Flow<String>
    suspend fun setUserToken(newUserToken: String)
    suspend fun isUserLoggedIn(): Flow<Boolean>
    suspend fun setUserLoginStatus(isUserLoggedIn: Boolean)
    suspend fun getCurrentUser(): Flow<UserResponse>
    suspend fun setCurrentUser(user: UserResponse)
}

class UserPreferenceDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson
) : UserPreferenceDataSource {

    override suspend fun clearData() {
        dataStore.edit {
            it.clear()
        }
    }

    override suspend fun getUserToken(): Flow<String> {
        return dataStore.data.map {
            it.toPreferences()[UserPreferenceKey.userToken].orEmpty()
        }
    }

    override suspend fun setUserToken(newUserToken: String) {
        dataStore.edit {
            it[UserPreferenceKey.userToken] = newUserToken
        }
    }

    override suspend fun isUserLoggedIn(): Flow<Boolean> {
        return dataStore.data.map {
            it.toPreferences()[UserPreferenceKey.isUserLoggedIn] ?: false
        }
    }

    override suspend fun setUserLoginStatus(isUserLoggedIn: Boolean) {
        dataStore.edit {
            it[UserPreferenceKey.isUserLoggedIn] = isUserLoggedIn
        }
    }

    override suspend fun getCurrentUser(): Flow<UserResponse> {
        return dataStore.data.map {
            gson.fromJson(
                it.toPreferences()[UserPreferenceKey.userObject].orEmpty(),
                UserResponse::class.java
            )
        }
    }

    override suspend fun setCurrentUser(user: UserResponse) {
        dataStore.edit {
            it[UserPreferenceKey.userObject] = gson.toJson(user)
        }
    }

}