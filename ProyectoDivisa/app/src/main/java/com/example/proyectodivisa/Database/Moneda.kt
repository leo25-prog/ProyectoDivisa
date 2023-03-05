package com.example.proyectodivisa.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Moneda (
    @PrimaryKey
    val code : String,
    val value : Double
)