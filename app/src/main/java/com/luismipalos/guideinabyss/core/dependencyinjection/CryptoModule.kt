package com.luismipalos.guideinabyss.core.dependencyinjection

import com.luismipalos.guideinabyss.core.security.PasswordHash
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CryptoModule {
    @Singleton
    @Provides
    fun provideCryptoPassword(): PasswordHash = PasswordHash()
}