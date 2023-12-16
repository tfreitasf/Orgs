package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import br.com.povengenharia.orgs.databinding.ActivityUserProfileBinding
import br.com.povengenharia.orgs.ui.activity.userproducts.UserProductListManager
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class UserProfileActivity : UserProductListManager() {

    private val binding by lazy {
        ActivityUserProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fillField()
        configureLogoutButton()
    }

    private fun fillField(){
        lifecycleScope.launch {
            user
                .filterNotNull()
                .collect { loggedUser ->
                    binding.tvActivityUserProfileUser.text = loggedUser.id
                    binding.tvActivityUserProfileName.text = loggedUser.name
                }
        }
    }

    private fun configureLogoutButton(){
        binding.btnActivityUserProfileLogout.setOnClickListener {
            lifecycleScope.launch {
                logoutUser()
            }
        }
    }
}