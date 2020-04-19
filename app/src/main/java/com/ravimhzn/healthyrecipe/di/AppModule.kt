package com.ravimhzn.healthyrecipe.di

import android.app.Application
import android.content.Context
import com.ravimhzn.healthyrecipe.AppExecutors
import com.ravimhzn.healthyrecipe.network.RecipeApiService
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeApiClient
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeListRepository
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

    /**
     * TODO("NEED TO SEPERATE THESE SCOPES INDIVIDUALLY")
     */

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RecipeApiService {
        return retrofit.create(RecipeApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkExecutors(): AppExecutors {
        return AppExecutors()
    }

    @Singleton
    @Provides
    fun provideRecipeApiClient(
        appExecutors: AppExecutors,
        recipeApiService: RecipeApiService
    ): RecipeApiClient {
        return RecipeApiClient(recipeApiService, appExecutors)
    }

    @Singleton
    @Provides
    fun provideRecipeListRepository(recipeApiClient: RecipeApiClient): RecipeListRepository {
        return RecipeListRepository(recipeApiClient)
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