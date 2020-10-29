/*
 * Copyright (c) 2020. Created by Mahmoud-dev
 * Preferences DataStore
 */

package com.mahmouddev.daynighttheme.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRef(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore("myDataStore")

    suspend fun setString(key: String, value: String) {
        val ks = preferencesKey<String>(key)
        dataStore.edit { preferences ->
            preferences[ks] = value
        }
    }

    fun getString(key: String): Flow<String?> {
        val ks = preferencesKey<String>(key)
        return dataStore.data.map { preferences ->
            preferences[ks]
        }
    }


    suspend fun setInt(key: String, value: Int) {
        val ks = preferencesKey<Int>(key)
        dataStore.edit { preferences ->
            preferences[ks] = value
        }
    }

    fun getInt(key: String): Flow<Int?> {
        val ks = preferencesKey<Int>(key)
        return dataStore.data.map { preferences ->
            preferences[ks]
        }
    }


    suspend fun setBoolean(key: String, value: Boolean) {
        val ks = preferencesKey<Boolean>(key)
        dataStore.edit { preferences ->
            preferences[ks] = value
        }
    }

    fun getBoolean(key: String): Flow<Boolean?> {
        val ks = preferencesKey<Boolean>(key)
        return dataStore.data.map { preferences ->
            preferences[ks]
        }
    }


    suspend fun setLong(key: String, value: Long) {
        val ks = preferencesKey<Long>(key)
        dataStore.edit { preferences ->
            preferences[ks] = value
        }
    }

    fun getLong(key: String): Flow<Long?> {
        val ks = preferencesKey<Long>(key)
        return dataStore.data.map { preferences ->
            preferences[ks]
        }
    }


    suspend fun setFloat(key: String, value: Float) {
        val ks = preferencesKey<Float>(key)
        dataStore.edit { preferences ->
            preferences[ks] = value
        }
    }

    fun getFloat(key: String): Flow<Float?> {
        val ks = preferencesKey<Float>(key)
        return dataStore.data.map { preferences ->
            preferences[ks]
        }
    }


}