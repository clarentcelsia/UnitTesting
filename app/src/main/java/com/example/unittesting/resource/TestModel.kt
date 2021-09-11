package com.example.unittesting.resource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbModel")
data class TestModel(
    var name: String,
    var stock: Int,
    var price: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)