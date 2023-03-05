package com.example.proyectodivisa.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Moneda (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var code : String,
    var value : Double
)