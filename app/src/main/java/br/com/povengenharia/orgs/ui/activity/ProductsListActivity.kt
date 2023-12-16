package br.com.povengenharia.orgs.ui.activity


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.databinding.ActivityProductsListBinding
import br.com.povengenharia.orgs.extensions.goTo
import br.com.povengenharia.orgs.ui.activity.userproducts.UserProductListManager
import br.com.povengenharia.orgs.ui.recyclerview.adapter.ProductsListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsListActivity : UserProductListManager() {

    private val binding by lazy {
        ActivityProductsListBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        ProductsListAdapter(this, scope = lifecycleScope).apply {
            whenClickDelete = { product ->
                lifecycleScope.launch {
                    AppDatabase.getInstance(this@ProductsListActivity).productDao()
                        .deleteProduct(product)
                    withContext(Dispatchers.Main) {
                        removeProduct(product)
                    }
                }
            }
            whenClickEdit = { product ->
                val editIntent =
                    Intent(this@ProductsListActivity, ProductFormActivity::class.java).apply {
                        putExtra(CHAVE_PRODUTO_ID, product.id)
                    }
                startActivity(editIntent)
            }
        }
    }

    private val productDao by lazy {
        val db = AppDatabase.getInstance(this)
        db.productDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        configureAddProductFab()
        lifecycleScope.launch {
            launch {
                user
                    .filterNotNull()
                    .collect {user ->
                        searchUserProducts(user.id)
                    }
            }
        }
    }


    private suspend fun searchUserProducts(userId: String) {
        productDao.fetchAllForUser(userId).collect { products ->
            adapter.update(products)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lifecycleScope.launch {

            when (item.itemId) {
                R.id.menu_produtct_list_account ->{
                    goTo(UserProfileActivity::class.java)
                }

                R.id.menu_product_list_logout -> {
                    logoutUser()
                }

                R.id.menu_show_all_products ->{
                    goTo(AllProductsActivity::class.java)
                }

                R.id.menu_order_name_ascending -> productDao.getAllOrderByNameAsc()
                    .collect { products -> adapter.update(products) }

                R.id.menu_order_name_descending -> productDao.getAllOrderByNameDesc()
                    .collect { products -> adapter.update(products) }

                R.id.menu_order_description_ascending -> productDao.getAllOrderByDescriptionAsc()
                    .collect { products -> adapter.update(products) }

                R.id.menu_order_description_descending -> productDao.getAllOrderByDescriptionDesc()
                    .collect { products -> adapter.update(products) }

                R.id.menu_order_price_high_to_low -> productDao.getAllOrderByPriceDesc()
                    .collect { products -> adapter.update(products) }

                R.id.menu_order_value_low_to_high -> productDao.getAllOrderByPriceAsc()
                    .collect { products -> adapter.update(products) }

                R.id.menu_order_none -> {
                    user.value?.let { loggedUser ->
                        searchUserProducts(loggedUser.id)
                    }
                }

                else -> return@launch
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun configureAddProductFab() {
        val fab = binding.fabAddProduct
        fab.setOnClickListener {
            openProductForm()
        }
    }

    private fun openProductForm() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvProductList
        recyclerView.adapter = adapter
        adapter.whenClickOnItem = {
            val intent = Intent(
                this,
                ProductDetailsActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
    }
}
