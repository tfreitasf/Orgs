package br.com.povengenharia.orgs.dao

import br.com.povengenharia.orgs.model.Product

class ProductsDao {

    fun add(product: Product){
        products.add(product)

    }

    fun getAllProducts() : List<Product>{
        return products.toList()


    }

    companion object {
        private val products = mutableListOf<Product>()
    }
}