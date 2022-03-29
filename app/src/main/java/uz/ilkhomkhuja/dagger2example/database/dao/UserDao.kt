package uz.ilkhomkhuja.dagger2example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.ilkhomkhuja.dagger2example.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(list: List<UserEntity>)

    @Query("SELECT * FROM UserEntity")
    suspend fun getUsers(): List<UserEntity>

}