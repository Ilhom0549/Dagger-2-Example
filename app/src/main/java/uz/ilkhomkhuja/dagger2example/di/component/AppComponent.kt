package uz.ilkhomkhuja.dagger2example.di.component

import dagger.Component
import uz.ilkhomkhuja.dagger2example.di.module.ApplicationModule
import uz.ilkhomkhuja.dagger2example.di.module.DatabaseModule
import uz.ilkhomkhuja.dagger2example.di.module.NetworkModule
import uz.ilkhomkhuja.dagger2example.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}