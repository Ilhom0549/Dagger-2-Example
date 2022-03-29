package uz.ilkhomkhuja.dagger2example.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.ilkhomkhuja.dagger2example.database.entity.UserEntity
import uz.ilkhomkhuja.dagger2example.repository.UserRepository
import uz.ilkhomkhuja.dagger2example.utils.NetworkHelper
import uz.ilkhomkhuja.dagger2example.utils.UserResource
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val stateFlow = MutableStateFlow<UserResource>(UserResource.Loading)

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                userRepository.getUsers()
                    .catch {
                        stateFlow.value = UserResource.Error(it.message ?: "Error")
                    }
                    .collect { response ->
                        if (response.isSuccessful) {
                            val list = ArrayList<UserEntity>()
                            response.body()?.forEach { userData ->
                                val userEntity = UserEntity(
                                    userData.id,
                                    userData.name,
                                    userData.username,
                                    userData.email
                                )
                                list.add(userEntity)
                            }
                            userRepository.addAllUsers(list)
                            stateFlow.value = UserResource.Success(userRepository.getDbUsers())
                        }
                    }

            } else {
                if (userRepository.getDbUsers().isNotEmpty()) {
                    stateFlow.value = UserResource.Success(userRepository.getDbUsers())
                } else {
                    stateFlow.value = UserResource.Error("Internet Disconnected !!")
                }
            }
        }

    }

    fun getStateFlow(): StateFlow<UserResource> {
        return stateFlow
    }
}