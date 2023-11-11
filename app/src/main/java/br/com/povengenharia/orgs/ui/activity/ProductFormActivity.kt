package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.database.AppDatabase
import br.com.povengenharia.orgs.databinding.ActivityProductFormBinding
import br.com.povengenharia.orgs.extensions.TryLoadImage
import br.com.povengenharia.orgs.model.Product
import br.com.povengenharia.orgs.ui.dialog.ImageDialogForm
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductFormBinding

    private var url: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.txt_register_product)

        saveNewProduct()

        binding.ivProductFormImage.setOnClickListener {
            ImageDialogForm(this).show(url) { image ->
                url = image
                binding.ivProductFormImage.TryLoadImage(url)
            }
        }
    }

    private fun saveNewProduct() {
        val btnSave = binding.btnProductFormSave
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()

        btnSave.setOnClickListener {
            val newProdutct = createNewProductFromForm()
            productDao.add(newProdutct)
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
            name = name,
            description = description,
            price = price,
            image = url
        )
    }
}