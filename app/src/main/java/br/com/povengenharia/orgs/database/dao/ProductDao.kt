package br.com.povengenharia.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.povengenharia.orgs.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getAll(): Flow<List<Product>>

    @Insert
    suspend fun add(vararg product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM Product WHERE id = :id")
    fun findById(id: Long): Flow<Product?>

    @Query("SELECT * FROM product ORDER BY name COLLATE NOCASE ASC")
    fun getAllOrderByNameAsc(): Flow<List<Product>>

    @Query("SELECT * FROM Product ORDER BY name COLLATE NOCASE DESC")
    fun getAllOrderByNameDesc(): Flow<List<Product>>

    @Query("SELECT * FROM Product ORDER BY description COLLATE NOCASE ASC")
    fun getAllOrderByDescriptionAsc(): Flow<List<Product>>

    @Query("SELECT * FROM Product ORDER BY description COLLATE NOCASE DESC")
    fun getAllOrderByDescriptionDesc(): Flow<List<Product>>

    @Query("SELECT * FROM Product ORDER BY price DESC")
    fun getAllOrderByPriceDesc(): Flow<List<Product>>

    @Query("SELECT * FROM Product ORDER BY price ASC")
    fun getAllOrderByPriceAsc(): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE userId = :userId")
    fun fetchAllForUser(userId: String) : Flow<List<Product>>
}