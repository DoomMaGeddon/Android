package com.luismipalos.guideinabyss.views.signUp.data.network

import com.luismipalos.guideinabyss.views.signUp.data.network.dto.UserDTO
import com.luismipalos.guideinabyss.views.signUp.data.network.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpClient {
    @Headers(
        "Content-Type: application/json"
    )
    @POST("user/register")
    suspend fun doSignUp(@Body user: UserDTO) : Response<SignUpResponse>
}