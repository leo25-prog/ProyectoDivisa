package com.example.proyectodivisa.Database

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Query

@Dao
interface MonedaDao {
    @Query("SELECT * FROM Moneda WHERE code = :code")
    fun getByCode(code: String) : Moneda

    @Query("select * from Moneda")
    fun getAllCursor(): Cursor

    @Query("select * from Moneda")
    fun getAll(): kotlinx.coroutines.flow.Flow<List<Moneda>>
}