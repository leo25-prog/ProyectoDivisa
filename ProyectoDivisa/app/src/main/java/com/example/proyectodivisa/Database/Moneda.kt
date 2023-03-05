package com.example.proyectodivisa.Database

import androidx.room.Entity

@Entity
data class Moneda (
    val code : String,
    val value : Double
)