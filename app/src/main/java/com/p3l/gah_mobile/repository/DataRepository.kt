package com.p3l.gah_mobile.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val dataStore : DataStore<Preferences>
) {
    val TOKEN = stringPreferencesKey("token")

    fun getToken() : Flow<String> = dataStore.data.map { preference ->
        preference[TOKEN] ?: ""
    }

    suspend fun setToken(token : String) {
        dataStore.edit { preference ->
            preference[TOKEN] = token
        }
    }
}