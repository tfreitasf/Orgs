package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.databinding.ActivityAllProductsBinding
import br.com.povengenharia.orgs.model.AllProductsItem
import br.com.povengenharia.orgs.ui.recyclerview.adapter.AllProductsAdapter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AllProductsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAllProductsBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        AllProductsAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        loadAllProducts()
    }

    private fun setupRecyclerView() {
        binding.rvActivityAllProductsAllProducts.adapter = adapter
    }

    private fun loadAllProducts() {
        val db = AppDatabase.getInstance(this)
        lifecycleScope.launch {


            val products = db.productDao().getAll().firstOrNull() ?: emptyList()
            val users = db.userDao().fetchAllUser().firstOrNull() ?: emptyList()

            val items = mutableListOf<AllProductsItem>()
            for (user in users) {
                items.add(AllProductsItem.UserTitle(user.name))
                val userProducts = products.filter { it.userId == user.id }
                userProducts.forEach { product ->
                    items.add(AllProductsItem.ProductItem(product))
                }
            }

            val productsWithoutUser = products.filter { it.savedWithoutUser() }
            if (productsWithoutUser.isNotEmpty()) {
                items.add(AllProductsItem.UserTitle("Sem usuário"))
                productsWithoutUser.forEach { product ->
                    items.add(AllProductsItem.ProductItem(product))
                }
            }
            adapter.updateItems(items)
        }
    }
}