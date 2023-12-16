package br.com.povengenharia.orgs.model

sealed class AllProductsItem {
    data class UserTitle(val userName: String) : AllProductsItem()
    data class ProductItem(val product: Product) : AllProductsItem()
}