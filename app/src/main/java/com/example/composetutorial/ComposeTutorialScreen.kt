package com.example.composetutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composetutorial.data.ContactData
import com.example.composetutorial.uinterface.screens.AccountScreen
import com.example.composetutorial.uinterface.screens.ConversationsScreen
import com.example.composetutorial.uinterface.screens.ConversationScreen

enum class ComposeTutorialScreen() {
    Conversations,
    Conversation,
    Account
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeTutorialAppBar(
    currentScreen: ComposeTutorialScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    title: String,
    navController : NavHostController
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(title)
        },
        navigationIcon = {
            if(canNavigateBack){
                IconButton(navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigation button"
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = {navController.navigate(ComposeTutorialScreen.Account.name)}) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "account icon"
                )
            }
        },

        modifier = Modifier.height(80.dp)
    )
}

@Composable
fun ComposeTutorialApp(
    viewModel: ContactViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ComposeTutorialScreen.valueOf(
        value = backStackEntry?.destination?.route ?: ComposeTutorialScreen.Conversations.name
    )

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            ComposeTutorialAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                title = currentScreen.name,
                navController = navController
            )
        }
    ) {
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)){
                NavHost(
                    navController = navController,
                    startDestination = ComposeTutorialScreen.Conversations.name
                ) {
                    composable(route = ComposeTutorialScreen.Conversations.name){
                        ConversationsScreen(contacts = ContactData.contactSample,contactClicked = {
                            contact -> viewModel.changeSelectedContact(contact)
                            navController.navigate(ComposeTutorialScreen.Conversation.name)})
                    }
                    composable(route = ComposeTutorialScreen.Conversation.name){
                        ConversationScreen(viewModel.selectedContact,
                            contactClicked = {})
                    }
                    composable(route = ComposeTutorialScreen.Account.name){
                        AccountScreen()
                    }
                }
            }


    }

}
