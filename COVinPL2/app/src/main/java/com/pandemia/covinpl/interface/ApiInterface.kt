package com.pandemia.covinpl.`interface`

import com.pandemia.covinpl.models.CovidModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/countries")
    fun getCovidData():  Call<List<CovidModel>>
}