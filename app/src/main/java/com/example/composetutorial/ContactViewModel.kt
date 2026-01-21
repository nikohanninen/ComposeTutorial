package com.example.composetutorial

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composetutorial.classes.Contact
import com.example.composetutorial.classes.Message

class ContactViewModel : ViewModel() {

    var selectedContact by mutableStateOf(Contact("null",0,listOf<Message>() ))
        private set

    fun changeSelectedContact(contact: Contact){
        selectedContact = contact
    }
}