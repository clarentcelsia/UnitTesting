package com.example.unittesting.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbModel")
data class TestModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var stock: Int,
    var price: Double
)