package uz.ilkhomkhuja.dagger2example.app

import android.app.Application
import uz.ilkhomkhuja.dagger2example.di.component.AppComponent
import uz.ilkhomkhuja.dagger2example.di.component.DaggerAppComponent
import uz.ilkhomkhuja.dagger2example.di.module.ApplicationModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}