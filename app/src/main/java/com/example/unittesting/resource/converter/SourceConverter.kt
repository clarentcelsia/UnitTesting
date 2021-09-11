package com.example.unittesting.resource.converter

import androidx.room.TypeConverter
import com.example.unittesting.resource.data.Source

class SourceConverter {

    @TypeConverter
    fun from(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun to(name: String): Source{
        return Source(name, name)
    }
}