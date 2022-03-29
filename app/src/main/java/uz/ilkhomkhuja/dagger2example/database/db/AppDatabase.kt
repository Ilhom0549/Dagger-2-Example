package uz.ilkhomkhuja.dagger2example.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.ilkhomkhuja.dagger2example.database.dao.UserDao
import uz.ilkhomkhuja.dagger2example.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}