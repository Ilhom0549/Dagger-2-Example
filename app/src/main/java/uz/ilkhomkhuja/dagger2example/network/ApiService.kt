package uz.ilkhomkhuja.dagger2example.network

import retrofit2.Response
import retrofit2.http.GET
import uz.ilkhomkhuja.dagger2example.models.UserData

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<UserData>>
}