package com.example.composetutorial.uinterface.screens

import android.R.color.white
import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetutorial.AppViewModelProvider
import com.example.composetutorial.MessageViewModel
import com.example.composetutorial.classes.Contact
import com.example.composetutorial.classes.Message
import com.example.composetutorial.data.ContactData
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

@Composable
fun MessageCard(msg: Message, contact: Contact) {
    val isMyMessage = msg.author == "You"

    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
    ) {
        if (isMyMessage) {
            Spacer(modifier = Modifier.weight(1f))
        } else {
            Image(
                painter = painterResource(contact.profilePicture),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(
                        1.5.dp,
                        MaterialTheme.colorScheme.primary,
                        CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(8.dp))
        }

        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            if (isExpanded) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surface
            },
            label = "messageColor"
        )

        Column(
            modifier = Modifier.clickable { isExpanded = !isExpanded }
        ) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ConversationScreen(
    contact: Contact,
    contactClicked: () -> Unit,
    messageViewModel: MessageViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var newMessageText by remember { mutableStateOf("") }

    val dbMessages by messageViewModel
        .getMessagesForContact(contact.name)
        .collectAsState(initial = emptyList())

    val allMessages = contact.messages + dbMessages.map {
        Message(
            author = it.author,
            body = it.body
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(allMessages) { message ->
                MessageCard(message, contact)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = newMessageText,
                onValueChange = { newMessageText = it },
                modifier = Modifier.weight(1f),
                label = { Text("Type a message") },
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(Color.White)

            )

            Button(
                modifier = Modifier.padding(top = 12.dp),
                onClick = {
                    if (newMessageText.isNotBlank()) {
                        messageViewModel.sendMessage(
                            contactName = contact.name,
                            body = newMessageText.trim()
                        )
                        newMessageText = ""
                    }
                }
            ) {
                Text("Send")
            }
        }
    }
}


@Preview
@Composable
fun PreviewConversationScreen(){
    ComposeTutorialTheme {
        ConversationScreen(
            contact = ContactData.contactSample[0],
            contactClicked = {})
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)


@Preview
@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageCard(Message("Android", "Jetpack Compose"), ContactData.contactSample[0])
        }
    }
}