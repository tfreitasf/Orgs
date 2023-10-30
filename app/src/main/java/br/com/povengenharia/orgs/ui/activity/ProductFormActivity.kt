package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.orgs.R
import br.com.povengenharia.orgs.dao.ProductsDao
import br.com.povengenharia.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)


        saveNewProduct()
    }

    private fun saveNewProduct() {
        val btnSave = findViewById<Button>(R.id.btn_product_form_save)
        val dao = ProductsDao()
        btnSave.setOnClickListener {
            val newProdutct = createNewProductFromForm()
            dao.add(newProdutct)
            finish()
        }
    }

    private fun createNewProductFromForm(): Product {
        val nameField = findViewById<EditText>(R.id.et_product_form_name)
        val descriptionField = findViewById<EditText>(R.id.et_product_form_description)
        val priceField = findViewById<EditText>(R.id.et_product_form_price)

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