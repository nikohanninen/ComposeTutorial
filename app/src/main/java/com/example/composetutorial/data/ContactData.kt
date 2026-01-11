package com.example.composetutorial.data

import com.example.composetutorial.R
import com.example.composetutorial.uinterface.screens.Contact

object ContactData {

    val contactSample = listOf(
        Contact("Lexi", SampleData.conversationSample[0], R.drawable.profile_picture),
        Contact(name = "Niko", latestMessage = SampleData.conversationSample[1], profilePicture = R.drawable.niko_profilepicture),

    )
}