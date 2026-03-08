package com.example.composetutorial.data

import kotlinx.coroutines.flow.Flow

class OfflineMessageRepository(private val messageDao: MessageDao) : MessageRepository{

    override fun getMessagesContact(contactName: String): Flow<List<Message>> = messageDao.getMessagesContact(contactName)

    override suspend fun insertMessage(message: Message) = messageDao.insert(message)
}