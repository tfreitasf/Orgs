package br.com.povengenharia.orgs.ui.activity.userproducts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.extensions.goTo
import br.com.povengenharia.orgs.model.User
import br.com.povengenharia.orgs.preferences.dataStore
import br.com.povengenharia.orgs.preferences.loggedUserIdPreferences
import br.com.povengenharia.orgs.ui.activity.LoginActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

abstract class UserProductListManager : AppCompatActivity() {

    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }

    private var _user: MutableStateFlow<User?> = MutableStateFlow(null)
    protected var user: StateFlow<User?> = _user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            checkLoggedUser()
        }
    }

    private suspend fun checkLoggedUser() {
        dataStore.data.collect { preferences ->
            preferences[loggedUserIdPreferences]?.let { userId ->
                fetchUser(userId)
            } ?: goToLogin()
        }
    }

    private suspend fun fetchUser(userId: String) {
        _user.value = userDao
            .fetchForId(userId)
            .firstOrNull()
    }

    protected suspend fun logoutUser() {
        dataStore.edit { prefereces ->
            prefereces.remove(loggedUserIdPreferences)
        }
    }

    private fun goToLogin() {
        goTo(LoginActivity::class.java){
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        finish()
    }
}