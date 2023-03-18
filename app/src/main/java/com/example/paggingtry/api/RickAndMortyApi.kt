package com.example.paggingtry.api

import com.example.paggingtry.models.Person
import com.example.paggingtry.models.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RickAndMortyApi {

    @GET("character/")
    suspend fun getUsersPage(@Query("page") page: Int): Result

}