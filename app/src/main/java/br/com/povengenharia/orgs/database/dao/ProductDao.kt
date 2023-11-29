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
    suspend fun getAll(): List<Product>

    @Insert
    suspend fun add(vararg product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM Product WHERE id = :id")
    suspend fun findById(id: Long) : Product?

    @Query("SELECT * FROM product ORDER BY name COLLATE NOCASE ASC")
    suspend fun getAllOrderByNameAsc() :List<Product>

    @Query("SELECT * FROM Product ORDER BY name COLLATE NOCASE DESC")
    suspend fun getAllOrderByNameDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY description COLLATE NOCASE ASC")
    suspend fun getAllOrderByDescriptionAsc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY description COLLATE NOCASE DESC")
    suspend fun getAllOrderByDescriptionDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY price DESC")
    suspend fun getAllOrderByPriceDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY price ASC")
    suspend fun getAllOrderByPriceAsc(): List<Product>
}