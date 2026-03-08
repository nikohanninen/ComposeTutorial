package com.example.composetutorial.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val contactName: String,
    val author: String,
    val body: String
)