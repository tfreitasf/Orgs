package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.orgs.databinding.ActivityRegisterUserFormBinding
import br.com.povengenharia.orgs.model.User

class RegisterUserFormActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRegisterUserFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBtnRegister()


    }

    private fun setupBtnRegister() {
        binding.btnActivityRegisterUserFormRegister.setOnClickListener {
            val newUser = registerUser()
            Log.i("RegisterUser", "onCreate: $newUser")
            finish()
        }
    }

    private fun registerUser(): User {
        val user = binding.etActivityRegisterUserFormName.text.toString()
        val name = binding.etActivityRegisterUserFormName.text.toString()
        val password = binding.etActivityRegisterUserFormPassword.text.toString()
        return User(user, name, password)
    }

}