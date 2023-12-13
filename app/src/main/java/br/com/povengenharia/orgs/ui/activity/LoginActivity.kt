package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.databinding.ActivityLoginBinding
import br.com.povengenharia.orgs.extensions.goTo
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
            Log.i("LoginActivity", "$user e $password")
            lifecycleScope.launch {
                userDao.login(user, password)?.let { user ->
                    dataStore.edit { preferences ->
                        preferences[loggedUserIdPreferences] = user.id
                    }
                    goTo(ProductsListActivity::class.java)
                } ?: Toast.makeText(this@LoginActivity, "Falha na autenticação", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun setupBtnRegister() {
        binding.btnActivityLoginRegisterUser.setOnClickListener {
            goTo(RegisterUserFormActivity::class.java)
        }
    }
}