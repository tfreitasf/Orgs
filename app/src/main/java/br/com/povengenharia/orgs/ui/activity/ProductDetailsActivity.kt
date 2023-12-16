package br.com.povengenharia.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.databinding.ActivityProductDetailsBinding
import br.com.povengenharia.orgs.extensions.TryLoadImage
import br.com.povengenharia.orgs.extensions.formatValueAsBrazilianCurrency
import br.com.povengenharia.orgs.model.Product
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {


    private var productId: Long = 0L
    private var product: Product? = null
    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    private val productDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryLoadProduct()
        findProduct()
    }

    private fun findProduct() {
        lifecycleScope.launch {
            productDao.findById(productId).collect { productFound ->
                product = productFound
                product?.let {
                    fillField(it)
                } ?: finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_product_details_delete -> {
                lifecycleScope.launch {
                    product?.let {
                        productDao.deleteProduct(it)
                    }
                    finish()
                }
            }
            R.id.menu_product_details_edit -> {
                Intent(this, ProductFormActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO_ID, productId)
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun tryLoadProduct() {
        productId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)

    }

    private fun fillField(productLoad: Product) {
        with(binding) {
            ivProductDetailsImage.TryLoadImage(productLoad.image)
            tvProductDetailsName.text = productLoad.name
            tvProductDetailsDescription.text = productLoad.description
            val priceFormated = formatValueAsBrazilianCurrency(productLoad.price)
            tvProductDetailsPrice.text = priceFormated
        }
    }
}