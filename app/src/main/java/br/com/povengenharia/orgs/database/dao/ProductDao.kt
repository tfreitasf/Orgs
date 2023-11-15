package br.com.povengenharia.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.povengenharia.orgs.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Insert
    fun add(vararg product: Product)

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)

    @Query("SELECT * FROM Product WHERE id = :id")
    fun findById(id: Long) : Product?

    @Query("SELECT * FROM product ORDER BY name COLLATE NOCASE ASC")
    fun getAllOrderByNameAsc() :List<Product>

    @Query("SELECT * FROM Product ORDER BY name COLLATE NOCASE DESC")
    fun getAllOrderByNameDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY description COLLATE NOCASE ASC")
    fun getAllOrderByDescriptionAsc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY description COLLATE NOCASE DESC")
    fun getAllOrderByDescriptionDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY price DESC")
    fun getAllOrderByPriceDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY price ASC")
    fun getAllOrderByPriceAsc(): List<Product>
}