package com.example.moviemaster.data.convertors

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Convertor {

    @TypeConverter
    fun fromString(value: String): List<Long> {
        val listType = object : TypeToken<List<Long>>() {}.type
        return Gson().fromJson(value, listType)
    }


    @TypeConverter
    fun fromList(list: List<Long>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}