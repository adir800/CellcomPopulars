package com.cellcom.cellcompopulars.api



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class APIClient private constructor() {
    private val myApi: Api
    fun getMyApi(): Api {
        return myApi
    }

    companion object {
        @get:Synchronized
        var instance: APIClient? = null
            get() {
                if (field == null) {
                    field = APIClient()
                }
                return field
            }
            private set
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        myApi = retrofit.create(Api::class.java)
    }
}