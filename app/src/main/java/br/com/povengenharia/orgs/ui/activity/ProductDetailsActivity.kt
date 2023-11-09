package br.com.povengenharia.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.orgs.databinding.ActivityProductDetailsBinding
import br.com.povengenharia.orgs.extensions.TryLoadImage
import br.com.povengenharia.orgs.extensions.formatValueAsBrazilianCurrency
import br.com.povengenharia.orgs.model.Product

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        tryLoadProduct()
    }

    private fun tryLoadProduct() {
        intent.getParcelableExtra<Product>("EXTRA_PRODUCT")?.let { productLoad ->
            fillField(productLoad)
        } ?: finish()
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