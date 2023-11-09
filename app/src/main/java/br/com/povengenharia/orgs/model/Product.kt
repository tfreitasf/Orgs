package br.com.povengenharia.orgs.model

import java.math.BigDecimal
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Product(
    val name: String,
    val description: String,
    val price: BigDecimal,
    val image: String? = null
) : Parcelable