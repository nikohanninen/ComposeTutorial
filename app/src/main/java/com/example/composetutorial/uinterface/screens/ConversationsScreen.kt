package com.example.composetutorial.uinterface.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetutorial.R
import com.example.composetutorial.data.ContactData

@Composable
fun ConversationsScreen(
    onNextButtonClicked: () -> Unit,
    contacts: List<Contact>){
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Color.Black) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, top = 8.dp)) {
            items(contacts){
                contact -> ContactCard(contact, onNextButtonClicked)
                HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier.padding(start = 64.dp))
            }
        }
    }
    }

//}

data class Contact(val name: String, val latestMessage: Message, val profilePicture: Int)

@Composable
fun ContactCard(
    contact: Contact,
    onNextButtonClicked: () -> Unit){

    var isExpanded by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .padding(vertical = 16.dp)
        .fillMaxWidth()
        .clickable {onNextButtonClicked()}) {
        Image(
            painter = painterResource(contact.profilePicture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(60.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(contact.name, fontSize = 22.sp, color = Color.White)
            Text(contact.latestMessage.body, fontSize = 12.sp,
                maxLines = 1, color = Color.White)
        }
    }
}

@Preview
@Composable
fun ConversationsScreenPreview(){
    ConversationsScreen(onNextButtonClicked = {}, ContactData.contactSample)
}



/*
        Button(onClick = onNextButtonClicked) {
            Text("Lexi")
        }
         */