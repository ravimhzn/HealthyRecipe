package com.ravimhzn.healthyrecipe.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ravimhzn.healthyrecipe.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Singleton
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //To support RxJava calls via Retrofit
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
//
//    @Singleton
//    @Provides
//    fun provideNoteDatabase(application: Application): CountryDatabase {
//        return Room.databaseBuilder(
//            application,
//            CountryDatabase::class.java,
//            DATABASE_NAME
//        ).fallbackToDestructiveMigration().build()// get correct db version if schema changed
//    }

//    @Singleton
//    @Provides
//    fun provideNoteDao(countryDatabase: CountryDatabase): CountryInfoDao {
//        return countryDatabase.getCountryDetailsFromDbDao()
//    }
}