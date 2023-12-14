package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.databinding.ActivityLoginBinding
import br.com.povengenharia.orgs.extensions.goTo
import br.com.povengenharia.orgs.extensions.toast
import br.com.povengenharia.orgs.model.User
import br.com.povengenharia.orgs.preferences.dataStore
import br.com.povengenharia.orgs.preferences.loggedUserIdPreferences
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBtnLogin()
        setupBtnRegister()
    }

    private fun setupBtnLogin() {
        binding.btnActivityLoginLogin.setOnClickListener {
            val user = binding.etActivityLoginUser.text.toString()
            val password = binding.etActivityLoginPassword.text.toString()
            authentication(user, password)
        }
    }

    private fun authentication(user: String, password: String) {
        lifecycleScope.launch {
            userDao.login(user, password)?.let { user ->
                saveLoggedUserDataStore(user)
                goTo(ProductsListActivity::class.java)
                finish()
            } ?: toast(getString(R.string.txt_toast_authentication_failure))
        }
    }

    private suspend fun saveLoggedUserDataStore(user: User) {
        dataStore.edit { preferences ->
            preferences[loggedUserIdPreferences] = user.id
        }
    }

    private fun setupBtnRegister() {
        binding.btnActivityLoginRegisterUser.setOnClickListener {
            goTo(RegisterUserFormActivity::class.java)
        }
    }
}