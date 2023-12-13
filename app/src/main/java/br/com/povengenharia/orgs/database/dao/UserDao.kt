package br.com.povengenharia.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.povengenharia.orgs.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM User WHERE id = :userId AND password = :userPassword")
    suspend fun login(userId: String, userPassword: String): User?

    @Query("SELECT * FROM user WHERE id = :userId")
    fun fetchForId(userId: String): Flow<User>
}