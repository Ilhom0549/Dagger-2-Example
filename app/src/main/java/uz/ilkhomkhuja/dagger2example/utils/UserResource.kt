package uz.ilkhomkhuja.dagger2example.utils

import uz.ilkhomkhuja.dagger2example.database.entity.UserEntity
import uz.ilkhomkhuja.dagger2example.models.UserData

sealed class UserResource {

    object Loading : UserResource()

    data class Success(val list: List<UserEntity>) : UserResource()

    data class Error(val msg: String) : UserResource()
}