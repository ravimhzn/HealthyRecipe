package com.ravimhzn.infosyscodingapplication.ui.di


import dagger.Module


@Module
class MainModule {

    /**
     * We don't have to declare @Singleton here since we are using scope binding.
     * It's already declared @Singleton in AppModule
     * This class references scoped bindings
     */
//    @Provides
//    fun provideApiService(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }
//
//    @Provides
//    fun provideCountryDataSource(countryRemoteDataSource: CountryRemoteDataSource): CountryDataSource =
//        countryRemoteDataSource
//
//    @Provides
//    fun provideRecyclerAdapter(): AboutRecyclerAdapter {
//        return AboutRecyclerAdapter()
//    }

}