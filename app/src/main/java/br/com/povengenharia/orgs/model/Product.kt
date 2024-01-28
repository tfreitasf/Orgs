package br.com.povengenharia.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal


@Entity
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val image: String? = null,
    val userId: String? = null
) : Parcelable {

    fun savedWithoutUser() = userId.isNullOrBlank() && id > 0L

    @Ignore
    val priceIsValid = !priceLessThanOrEqualsZero() && !priceGreaterThanOneHundred()

    private fun priceLessThanOrEqualsZero(): Boolean{
        return price <= BigDecimal.ZERO
    }

    private fun priceGreaterThanOneHundred(): Boolean{
        return price > BigDecimal(100)
    }

}