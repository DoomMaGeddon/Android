package com.luismipalos.guideinabyss.views.delvers.data.network

import com.luismipalos.guideinabyss.views.delvers.data.network.dto.DelverDTO
import com.luismipalos.guideinabyss.views.layers.data.network.response.PostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface DelversClient {
    @Headers(
        "Content-Type: application/json"
    )
    @GET("delver")
    suspend fun doGetDelvers() : Response<List<DelverDTO>?>

    @POST("delver")
    suspend fun doPostDelver(
        @Header("auth-token") token:String,
        @Body newDelver: DelverDTO
    ) : Response<PostResponse>
}