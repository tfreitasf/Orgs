package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.dao.ProductsDao
import br.com.povengenharia.orgs.databinding.ActivityProductFormBinding
import br.com.povengenharia.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        saveNewProduct()
    }

    private fun saveNewProduct() {
        val btnSave = binding.btnProductFormSave
        val dao = ProductsDao()
        btnSave.setOnClickListener {
            val newProdutct = createNewProductFromForm()
            dao.add(newProdutct)
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
            price = price
        )
    }
}