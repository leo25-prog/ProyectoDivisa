package com.example.proyectodivisa.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Moneda (
<<<<<<< HEAD
    @PrimaryKey
    val code : String,
    val value : Double
=======
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var code : String,
    var value : Double
>>>>>>> a6fd807aa77d7e8053a6fb8dd4731c25861691f8
)