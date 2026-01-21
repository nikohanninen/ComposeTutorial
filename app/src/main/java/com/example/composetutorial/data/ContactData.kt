package com.example.composetutorial.data

import com.example.composetutorial.R
import com.example.composetutorial.classes.Contact

object ContactData {

    val contactSample = listOf(
        Contact("Lexi", R.drawable.profile_picture, messages = SampleData.conversationLexi),
        Contact(name = "Niko", profilePicture = R.drawable.niko_profilepicture, messages = SampleData.conversationNiko),
    )
}