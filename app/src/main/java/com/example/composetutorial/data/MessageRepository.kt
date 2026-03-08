package com.example.composetutorial.data

import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    fun getMessagesContact(contactName: String): Flow<List<Message>>

    suspend fun insertMessage(message: Message)

}