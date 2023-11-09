package br.com.povengenharia.orgs.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun formatValueAsBrazilianCurrency(price: BigDecimal): String? {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return currencyFormat.format(price)
}