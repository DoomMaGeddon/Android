package com.luismipalos.guideinabyss.core.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUserDao
import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideLoggedUserDatabase(@ApplicationContext c: Context): LoggedUserDatabase =
        Room.databaseBuilder(
            c,
            LoggedUserDatabase::class.java,
            "loggedUserDatabase"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideLoggedUserDao(database: LoggedUserDatabase): LoggedUserDao = database.loggedUserDao()
}