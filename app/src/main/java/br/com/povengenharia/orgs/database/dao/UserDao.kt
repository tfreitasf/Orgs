package br.com.povengenharia.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.povengenharia.orgs.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)
}