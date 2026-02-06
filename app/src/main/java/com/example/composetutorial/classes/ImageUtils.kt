package com.example.composetutorial.classes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File

object ImageUtils {

    fun copyImageToAppStorage(
        context: Context,
        sourceUri: Uri
    ): String {
        context.contentResolver.openInputStream(sourceUri)?.use { input ->
            val file = File(
                context.filesDir,
                "profilePicture"
            )

            file.outputStream().use { output ->
                input.copyTo(output)
            }
            return file.absolutePath
        }

        error("Failed to open input stream")
    }
}