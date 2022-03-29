package uz.ilkhomkhuja.dagger2example.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import uz.ilkhomkhuja.dagger2example.app.App
import javax.inject.Singleton

@Module
class ApplicationModule(var app: App) {

    @Provides
    @Singleton
    fun provideApp(): App = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

}