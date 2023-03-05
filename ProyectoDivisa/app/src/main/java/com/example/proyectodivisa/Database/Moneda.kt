package com.example.proyectodivisa.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Moneda (
    @PrimaryKey
    var code : String,
    var value : Double
)