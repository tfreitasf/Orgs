package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.orgs.databinding.ActivityLoginBinding
import br.com.povengenharia.orgs.extensions.goTo

class LoginActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
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
            goTo(ProductsListActivity::class.java)
        }
    }

    private fun setupBtnRegister() {
        binding.btnActivityLoginRegisterUser.setOnClickListener {
            goTo(RegisterUserFormActivity::class.java)
        }
    }
}