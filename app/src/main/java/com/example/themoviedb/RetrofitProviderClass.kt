package com.example.themoviedb

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProviderClass {

        companion object {

            fun provideRetrofit(): TMDBInterface {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/search/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                return retrofit.create(TMDBInterface::class.java)
            }
        }
}