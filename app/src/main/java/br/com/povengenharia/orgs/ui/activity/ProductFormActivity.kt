package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.database.dao.ProductDao
import br.com.povengenharia.orgs.databinding.ActivityProductFormBinding
import br.com.povengenharia.orgs.extensions.TryLoadImage
import br.com.povengenharia.orgs.model.Product
import br.com.povengenharia.orgs.ui.activity.userproducts.UserProductListManager
import br.com.povengenharia.orgs.ui.dialog.ImageDialogForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class ProductFormActivity : UserProductListManager() {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private var productId = 0L

    private val productDao: ProductDao by lazy {
        val db = AppDatabase.getInstance(this)
        db.productDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = getString(R.string.txt_register_product)

        saveNewProduct()

        binding.ivProductFormImage.setOnClickListener {
            ImageDialogForm(this)
                .show(url) { image ->
                    url = image
                    binding.ivProductFormImage.TryLoadImage(url)
                }
        }
        tryLoadProduct()

    }

    private fun tryLoadProduct() {
        productId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    override fun onResume() {
        super.onResume()
        tryFindProduct()
    }

    private fun tryFindProduct() {
        lifecycleScope.launch {
            productDao.findById(productId).firstOrNull().let { product ->
                withContext(Dispatchers.Main) {
                    product?.let {
                        fillFields(it)
                        title = getString(R.string.txt_alterar_produto)
                    }
                }
            }
        }
    }

    private fun fillFields(product: Product) {
        url = product.image
        binding.ivProductFormImage.TryLoadImage(product.image)
        binding.etProductFormName.setText(product.name)
        binding.etProductFormDescription.setText(product.description)
        binding.etProductFormPrice.setText(product.price.toPlainString())
    }

    private fun saveNewProduct() {
        val btnSave = binding.btnProductFormSave
        btnSave.setOnClickListener {

            lifecycleScope.launch {
                user.value?.let {user ->
                    val newProduct = createNewProductFromForm(user.id)
                    if (productId > 0) {
                        productDao.updateProduct(newProduct)
                    } else {
                        productDao.add(newProduct)
                    }
                    finish()
                }

            }
        }
    }

    private fun createNewProductFromForm(userId: String): Product {
        val nameField = binding.etProductFormName
        val descriptionField = binding.etProductFormDescription
        val priceField = binding.etProductFormPrice

        val name = nameField.text.toString()
        val description = descriptionField.text.toString()
        val priceInText = priceField.text.toString()
        val price = if (priceInText.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(priceInText)
        }


        return Product(
            id = productId,
            name = name,
            description = description,
            price = price,
            image = url,
            userId = userId
        )
    }
}