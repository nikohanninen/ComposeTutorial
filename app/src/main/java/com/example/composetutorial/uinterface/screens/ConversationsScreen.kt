package com.example.composetutorial.uinterface.screens

import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.composetutorial.AccountViewModel
import com.example.composetutorial.R
import com.example.composetutorial.classes.Contact
import com.example.composetutorial.data.ContactData

@Composable
fun ConversationsScreen(
    contactClicked: (Contact) -> Unit,
    contacts: List<Contact>,
    accountViewModel: AccountViewModel,
    sensorClicked: () -> Unit){
    Column() {
        Surface(modifier = Modifier,
            color = Color.Black) {
            LazyColumn(modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)) {
                items(contacts){
                        contact -> ContactCard(contact, contactClicked)
                    HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier.padding(start = 64.dp))
                }
            }
    }
        AccountCard(accountViewModel)
        Button(onClick = sensorClicked) {
            Text("Sensor")
        }
    }
    }

@Composable
fun AccountCard(
    viewModel: AccountViewModel
){
    Row(modifier = Modifier
        .padding(vertical = 16.dp)
        .fillMaxWidth()
            ) {
        AsyncImage(
            model = viewModel.accountState.profilePicture,
            placeholder = painterResource(R.drawable.profile_picture),
            contentDescription = "Account profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
                .border(
                    1.5.dp,
                    MaterialTheme.colorScheme.primary,
                    CircleShape
                )
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(viewModel.accountState.name, fontSize = 22.sp, color = Color.White)
        }
    }
}

@Composable
fun ContactCard(
    contact: Contact,
    contactClicked: (Contact) -> Unit){

    Row(modifier = Modifier
        .padding(vertical = 16.dp)
        .fillMaxWidth()
        .clickable {
            contactClicked(contact)}) {
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
            Text(contact.messages[0].body, fontSize = 12.sp,
                maxLines = 1, color = Color.White)
        }
    }
}

/*
@Preview
@Composable
fun ConversationsScreenPreview(){
    ConversationsScreen(contactClicked = {}, ContactData.contactSample)
}

 */



/*
        Button(onClick = contactClicked) {
            Text("Lexi")
        }
         */