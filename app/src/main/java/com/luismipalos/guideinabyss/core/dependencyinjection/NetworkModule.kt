package com.luismipalos.guideinabyss.core.dependencyinjection

import com.google.gson.GsonBuilder
import com.luismipalos.guideinabyss.views.artifacts.data.network.ArtifactsClient
import com.luismipalos.guideinabyss.views.delvers.data.network.DelversClient
import com.luismipalos.guideinabyss.views.layers.data.network.LayersClient
import com.luismipalos.guideinabyss.views.profile.data.network.ProfileClient
import com.luismipalos.guideinabyss.views.signIn.data.network.SignInClient
import com.luismipalos.guideinabyss.views.signUp.data.network.SignUpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("") //IP de la API
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                .setLenient()
                .create()
            )
        ).build()

    //Ejemplo de provider:
    @Singleton
    @Provides
    fun provideSignInClient(retrofit: Retrofit): SignInClient =
        retrofit.create(SignInClient::class.java)

    @Singleton
    @Provides
    fun provideSignUpClient(retrofit: Retrofit): SignUpClient =
        retrofit.create(SignUpClient::class.java)

    @Singleton
    @Provides
    fun provideLayersClient(retrofit: Retrofit): LayersClient =
        retrofit.create(LayersClient::class.java)

    @Singleton
    @Provides
    fun provideArtifactsClient(retrofit: Retrofit): ArtifactsClient =
        retrofit.create(ArtifactsClient::class.java)

    @Singleton
    @Provides
    fun provideDelversClient(retrofit: Retrofit): DelversClient =
        retrofit.create(DelversClient::class.java)

    @Singleton
    @Provides
    fun provideProfileClient(retrofit: Retrofit): ProfileClient =
        retrofit.create(ProfileClient::class.java)
}
