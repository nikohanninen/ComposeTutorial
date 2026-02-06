package com.example.composetutorial.classes

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.TypeConverter

class TypeConverter {

    @TypeConverter
    public fun uriToString(uri: Uri): String {
        return uri.toString()
    }

    @TypeConverter
    public fun stringToUri(value: String): Uri {
        return value.toUri()
    }
}