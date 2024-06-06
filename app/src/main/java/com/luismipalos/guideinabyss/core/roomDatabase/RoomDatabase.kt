package com.luismipalos.guideinabyss.core.roomDatabase

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity("loggedUser")
data class LoggedUser(
    @PrimaryKey
    val token: String,
    val email: String,
    val nombreUsuario: String,
    val fotoPerfil: String?,
    val descripcion: String?,
    val rol: String,
    val experiencia: Int,
    val rango: Int?,
    val notificaciones: Boolean
)

@Dao
interface LoggedUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoggedUser(loggedUser: LoggedUser)

    @Query("SELECT * FROM loggedUser")
    suspend fun getLoggedUser(): LoggedUser?

    @Query("DELETE FROM loggedUser")
    suspend fun deleteLoggedUser()
}

@Database(entities = [LoggedUser::class], version = 4)
abstract class LoggedUserDatabase : RoomDatabase() {
    abstract fun loggedUserDao(): LoggedUserDao
}