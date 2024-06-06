package com.luismipalos.guideinabyss.views.layers.data.network

import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaFormularyDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraFormularyDTO
import com.luismipalos.guideinabyss.views.layers.data.network.response.PostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface LayersClient {
    @Headers(
        "Content-Type: application/json"
    )
    @GET("flora/{num}")
    suspend fun doGetLayerFlora(@Path("num") num: Int) : Response<List<FloraDTO>>

    @GET("fauna/{num}")
    suspend fun doGetLayerFauna(@Path("num") num: Int) : Response<List<FaunaDTO>>

    @POST("fauna")
    suspend fun doPostFauna(
        @Header("auth-token") token:String,
        @Body newAnimal: FaunaFormularyDTO
    ) : Response<PostResponse>

    @POST("flora")
    suspend fun doPostFlora(
        @Header("auth-token") token:String,
        @Body newAnimal: FloraFormularyDTO
    ) : Response<PostResponse>
}