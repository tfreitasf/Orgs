package br.com.povengenharia.orgs.dao

import br.com.povengenharia.orgs.model.Product
import java.math.BigDecimal

class ProductsDao {

    fun add(product: Product) {
        products.add(product)

    }

    fun getAllProducts(): List<Product> {
        return products.toList()


    }

    companion object {
        private val products = mutableListOf<Product>(
            Product(
                name = "Cesta 1",
                description = "Uva, Banana, Maça",
                price = BigDecimal("19.99")
            )
        )
    }
}