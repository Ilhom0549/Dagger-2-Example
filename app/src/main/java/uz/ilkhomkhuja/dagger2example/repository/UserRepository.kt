package uz.ilkhomkhuja.dagger2example.repository

import kotlinx.coroutines.flow.flow
import uz.ilkhomkhuja.dagger2example.database.dao.UserDao
import uz.ilkhomkhuja.dagger2example.database.entity.UserEntity
import uz.ilkhomkhuja.dagger2example.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    suspend fun getUsers() = flow { emit(apiService.getUsers()) }

    suspend fun addAllUsers(users: List<UserEntity>) = userDao.addAll(users)

    suspend fun getDbUsers() = userDao.getUsers()
}