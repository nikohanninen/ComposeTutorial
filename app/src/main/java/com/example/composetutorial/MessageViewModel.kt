package com.example.composetutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetutorial.data.Message
import com.example.composetutorial.data.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MessageViewModel(
    private val messageRepository: MessageRepository
) : ViewModel() {

    fun getMessagesForContact(contactName: String): Flow<List<Message>> {
        return messageRepository.getMessagesContact(contactName)
    }

    fun sendMessage(contactName: String, body: String) {
        viewModelScope.launch {
            messageRepository.insertMessage(
                Message(
                    contactName = contactName,
                    author = "You",
                    body = body
                )
            )
        }
    }
}