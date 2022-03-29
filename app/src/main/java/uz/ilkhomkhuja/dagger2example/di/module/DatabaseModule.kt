package uz.ilkhomkhuja.dagger2example.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uz.ilkhomkhuja.dagger2example.database.dao.UserDao
import uz.ilkhomkhuja.dagger2example.database.db.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "users.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

}