package com.ravimhzn.healthyrecipe.di


import android.app.Application
import com.ravimhzn.healthyrecipe.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Define in AndroidManifest
 */
//class BaseApplication : Application(), HasActivityInjector {
//
//    @Inject
//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
//
//    override fun onCreate() {
//        super.onCreate()
//        AppInjector.init(this)
//    }
//
//    override fun activityInjector() = dispatchingAndroidInjector
//}

open class BaseApplication : Application(), HasAndroidInjector {//} DaggerApplication(){

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

}