package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
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

    private val userNameToIdMap = mutableMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = getString(R.string.txt_register_product)
    }

    override fun onResume() {
        super.onResume()
        setupSaveButton()
        setupImageView()
        tryLoadProduct()
    }

    private fun setupImageView() {
        binding.ivProductFormImage.setOnClickListener {
            ImageDialogForm(this)
                .show(url) { image ->
                    url = image
                    binding.ivProductFormImage.TryLoadImage(url)
                }
        }
    }

    private fun tryLoadProduct() {
        productId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
        if (productId > 0) {
            loadProduct()
        }
    }

    private fun validExistingUser (): Boolean{
        val selectedUsername = binding.autoCompleteActivityProductForm.text.toString()
        if (!userNameToIdMap.containsKey(selectedUsername)){
            binding.autoCompleteActivityProductForm.error = "Usuário não encontrado!"
            return false
        }
        return true
    }

    private fun loadProduct() {
        lifecycleScope.launch {
            productDao.findById(productId).firstOrNull()?.let { product ->
                withContext(Dispatchers.Main) {
                    fillFields(product)
                    title = getString(R.string.txt_alterar_produto)
                    if (product.savedWithoutUser()) {
                        setupAutocompleteForNewProduct()
                    } else {
                        binding.autoCompleteActivityProductForm.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun fillFields(product: Product) {
        with(binding) {
            url = product.image
            ivProductFormImage.TryLoadImage(product.image)
            etProductFormName.setText(product.name)
            etProductFormDescription.setText(product.description)
            etProductFormPrice.setText(product.price.toPlainString())
        }
    }

    private fun setupSaveButton() {
        binding.btnProductFormSave.setOnClickListener {
            lifecycleScope.launch {
                saveProduct()
            }
        }
    }

    private suspend fun saveProduct() {
        if (binding.autoCompleteActivityProductForm.visibility == View.VISIBLE && !validExistingUser()) {
            return
        }
        val newProduct = createProductFromForm()
        if (productId > 0) {
            productDao.updateProduct(newProduct)
        } else {
            productDao.add(newProduct)
        }
        finish()
    }

    private fun createProductFromForm(): Product {
        val name = binding.etProductFormName.text.toString()
        val description = binding.etProductFormDescription.text.toString()
        val price = binding.etProductFormPrice.text.toString().toBigDecimalOrNull() ?: BigDecimal.ZERO
        val selectedUserName = binding.autoCompleteActivityProductForm.text.toString()
        val userId = userNameToIdMap[selectedUserName]

        return Product(
            id = productId,
            name = name,
            description = description,
            price = price,
            image = url,
            userId = userId ?: user.value?.id
        )
    }

    private fun setupAutocompleteForNewProduct() {
        lifecycleScope.launch {
            userDao.fetchAllUser().firstOrNull()?.let { users ->

                users.forEach { user ->
                    userNameToIdMap[user.name] = user.id
                }
                val adapter = ArrayAdapter(this@ProductFormActivity, android.R.layout.simple_dropdown_item_1line, users.map { it.name })
                withContext(Dispatchers.Main) {
                    binding.autoCompleteActivityProductForm.apply {
                        setAdapter(adapter)
                        visibility = if (users.isNotEmpty()) View.VISIBLE else View.GONE
                    }
                }
            }
        }
    }
}