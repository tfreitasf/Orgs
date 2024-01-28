package br.com.povengenharia.orgs.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: String,
    val name: String,
    val password: String,
    val email: String
) {


    @Ignore
    val passwordIsValid = password.length >= 6

    @Ignore
    val emailIsValid = email.matches(Regex("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}"))


}




