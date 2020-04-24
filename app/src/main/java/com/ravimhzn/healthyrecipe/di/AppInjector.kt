package com.ravimhzn.healthyrecipe.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.ravimhzn.openweatherapp.di.DaggerAppComponent
import dagger.android.AndroidInjection


object AppInjector {
//    fun init(app: BaseApplication) {
//        DaggerAppComponent
//            .builder()
//            .application(app)
//            .build()
//            .inject(app)


    fun init(app: BaseApplication) {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(app)) //Make sure you name appModule and (AppModule(app)) Same {Dagger thing}
            .build()
            .inject(app)

        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }

        })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is Injectable) {
            AndroidInjection.inject(activity)
        }

    }
}