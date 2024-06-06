package com.luismipalos.guideinabyss.views.signIn.data.network

import com.luismipalos.guideinabyss.views.signIn.data.network.dto.UserDTO
import com.luismipalos.guideinabyss.views.signIn.data.network.response.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInClient {
    @Headers(
        "Content-Type: application/json"
    )
    @POST("user/login")
    suspend fun doSignIn(@Body user: UserDTO) : Response<SignInResponse>
}