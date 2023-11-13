package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.database.dao.ProductDao
import br.com.povengenharia.orgs.databinding.ActivityProductFormBinding
import br.com.povengenharia.orgs.extensions.TryLoadImage
import br.com.povengenharia.orgs.model.Product
import br.com.povengenharia.orgs.ui.dialog.ImageDialogForm
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductFormBinding

    private var url: String? = null
    private var productId = 0L

    private val productDao: ProductDao by lazy {
        val db = AppDatabase.getInstance(this)
        db.productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
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
        productDao.findById(productId)?.let {
            title = getString(R.string.txt_alterar_produto)
            fillFields(it)
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
            val newProdutct = createNewProductFromForm()
            if (productId > 0) {
                productDao.updateProduct(newProdutct)
            } else {
                productDao.add(newProdutct)
            }
            finish()
        }
    }

    private fun createNewProductFromForm(): Product {
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
            image = url
        )
    }
}