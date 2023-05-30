package com.example.vamz_sem.filmy

import androidx.room.TypeConverter

class ListConverter {
        @TypeConverter
        fun fromList(list: List<String>?): String? {
            return list?.joinToString(",")
        }

        @TypeConverter
        fun toList(string: String?): List<String>? {
            return string?.split(",")?.map { it.trim() }
        }
}