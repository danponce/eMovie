package com.danponce.emovie.utils.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val TAG: String = "UserPreferencesRepo"

    private object PreferencesKeys {
        val LANGUAGE_FILTER = stringPreferencesKey("filter_language")
        val RELEASE_YEAR_FILTER = stringPreferencesKey("filter_release_year")
    }

    /**
     * Get the user preferences flow.
     */
    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapUserPreferences(preferences)
        }


    suspend fun fetchInitialPreferences() =
        mapUserPreferences(dataStore.data.first().toPreferences())

    suspend fun updateLanguageFilter(language: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LANGUAGE_FILTER] = language
        }
    }

    suspend fun updateReleaseYearFilter(releaseYear: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.RELEASE_YEAR_FILTER] = releaseYear
        }
    }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        // Get the language filter stored
        val languageFilter = preferences[PreferencesKeys.LANGUAGE_FILTER] ?: ""

        // Get the release year stored
        val releaseYearFilter = preferences[PreferencesKeys.RELEASE_YEAR_FILTER] ?: ""

        return UserPreferences(languageFilter, releaseYearFilter)
    }
}

data class UserPreferences(
    val languageFilter: String,
    val releaseYearFilter: String
)