package br.com.povengenharia.orgs.ui.activity


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.databinding.ActivityProductsListBinding
import br.com.povengenharia.orgs.ui.recyclerview.adapter.ProductsListAdapter

class ProductsListActivity : AppCompatActivity() {


    private val adapter = ProductsListAdapter(context = this)
    private lateinit var binding: ActivityProductsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        configureAddProductFab()
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()
        adapter.update(productDao.getAll())

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
