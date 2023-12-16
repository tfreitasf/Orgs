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

    @Query("SELECT * FROM product WHERE userId = :userId ORDER BY name COLLATE NOCASE ASC")
    fun getAllOrderByNameAsc(userId: String): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY name COLLATE NOCASE DESC")
    fun getAllOrderByNameDesc(userId: String): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY description COLLATE NOCASE ASC")
    fun getAllOrderByDescriptionAsc(userId: String): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY description COLLATE NOCASE DESC")
    fun getAllOrderByDescriptionDesc(userId: String): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY price DESC")
    fun getAllOrderByPriceDesc(userId: String): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY price ASC")
    fun getAllOrderByPriceAsc(userId: String): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE userId = :userId")
    fun fetchAllForUser(userId: String) : Flow<List<Product>>
}