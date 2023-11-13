package br.com.povengenharia.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun fromDouble(price: Double?): BigDecimal {
        return price?.let { BigDecimal(price.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun forBigDecimal(price: BigDecimal?): Double? {
        return price?.let { price.toDouble() }
    }
}