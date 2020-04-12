package com.ravimhzn.openweatherapp.di

import com.ravimhzn.healthyrecipe.di.ActivityBuildersModule
import com.ravimhzn.healthyrecipe.di.AppModule
import com.ravimhzn.healthyrecipe.di.BaseApplication
import com.ravimhzn.healthyrecipe.di.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)

interface AppComponent {
    fun inject(app: BaseApplication)
}

