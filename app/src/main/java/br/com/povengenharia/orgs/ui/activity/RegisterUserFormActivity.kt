package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.databinding.ActivityRegisterUserFormBinding
import br.com.povengenharia.orgs.model.User
import kotlinx.coroutines.launch

class RegisterUserFormActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRegisterUserFormBinding.inflate(layoutInflater)
    }

    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
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
            lifecycleScope.launch {
                try {
                    userDao.insert(newUser)
                    finish()
                } catch (e: Exception) {
                    Log.e("CadastroUsuario", "configuraBotaoCadastrar: ", e)
                    Toast.makeText(
                        this@RegisterUserFormActivity,
                        "Falha ao cadastrar usuário",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun registerUser(): User {
        val user = binding.etActivityRegisterUserFormUser.text.toString()
        val name = binding.etActivityRegisterUserFormName.text.toString()
        val password = binding.etActivityRegisterUserFormPassword.text.toString()
        return User(user, name, password)
    }

}