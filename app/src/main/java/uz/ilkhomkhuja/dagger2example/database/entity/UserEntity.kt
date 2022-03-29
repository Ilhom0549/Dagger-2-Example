package uz.ilkhomkhuja.dagger2example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val email: String
)