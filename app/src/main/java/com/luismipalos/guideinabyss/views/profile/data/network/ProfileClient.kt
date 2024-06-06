package com.luismipalos.guideinabyss.views.profile.data.network

import com.luismipalos.guideinabyss.views.profile.data.network.dto.FormularyDTO
import com.luismipalos.guideinabyss.views.profile.data.network.dto.ProfileDTO
import com.luismipalos.guideinabyss.views.profile.data.network.response.ArtifactHistoryResponse
import com.luismipalos.guideinabyss.views.profile.data.network.response.DelverHistoryResponse
import com.luismipalos.guideinabyss.views.profile.data.network.response.FaunaHistoryResponse
import com.luismipalos.guideinabyss.views.profile.data.network.response.FloraHistoryResponse
import com.luismipalos.guideinabyss.views.profile.data.network.response.ProfileResponse
import com.luismipalos.guideinabyss.views.profile.data.network.response.SignOutResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface ProfileClient {
    @Headers(
        "Content-Type: application/json"
    )

    @PUT("user/me/update")
    suspend fun doUpdate(
        @Header("auth-token") token:String,
        @Body user: ProfileDTO
    ) : Response<ProfileResponse>

    @POST("request")
    suspend fun doSubmitFormulary(
        @Header("auth-token") token:String,
        @Body formulary: FormularyDTO
    ) : Response<ProfileResponse>

    @POST("user/logout")
    suspend fun doSignOut(@Header("auth-token") token: String) : Response<SignOutResponse>

    @GET("fauna/me/history")
    suspend fun doGetFaunaHistory(@Header("auth-token") token: String) : Response<FaunaHistoryResponse>

    @GET("flora/me/history")
    suspend fun doGetFloraHistory(@Header("auth-token") token: String) : Response<FloraHistoryResponse>

    @GET("artifact/me/history")
    suspend fun doGetArtifactHistory(@Header("auth-token") token: String) : Response<ArtifactHistoryResponse>

    @GET("delver/me/history")
    suspend fun doGetDelverHistory(@Header("auth-token") token: String) : Response<DelverHistoryResponse>
}