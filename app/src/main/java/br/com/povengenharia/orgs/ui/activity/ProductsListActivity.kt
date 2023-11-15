package br.com.povengenharia.orgs.ui.activity


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.databinding.ActivityProductsListBinding
import br.com.povengenharia.orgs.model.Product
import br.com.povengenharia.orgs.ui.recyclerview.adapter.ProductsListAdapter

class ProductsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsListBinding

    private val adapter by lazy {
        ProductsListAdapter(this).apply {
            whenClickDelete = { product ->
                AppDatabase.getInstance(this@ProductsListActivity).productDao()
                    .deleteProduct(product)
                removeProduct(product)
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
        binding = ActivityProductsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        configureAddProductFab()
    }


    override fun onResume() {
        super.onResume()
        val productDao = AppDatabase.getInstance(this).productDao()
        adapter.update(productDao.getAll())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val productSort: List<Product>? = when (item.itemId) {
            R.id.menu_order_name_ascending -> productDao.getAllOrderByNameAsc()
            R.id.menu_order_name_descending -> productDao.getAllOrderByNameDesc()
            R.id.menu_order_description_ascending -> productDao.getAllOrderByDescriptionAsc()
            R.id.menu_order_description_descending -> productDao.getAllOrderByDescriptionDesc()
            R.id.menu_order_price_high_to_low -> productDao.getAllOrderByPriceDesc()
            R.id.menu_order_value_low_to_high -> productDao.getAllOrderByPriceAsc()
            R.id.menu_order_none -> productDao.getAll()
            else -> null
        }
        productSort?.let {
            adapter.update(it)
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
