package com.example.composetutorial

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composetutorial.uinterface.screens.ConversationsScreen
import com.example.composetutorial.uinterface.screens.ConversationScreen
import com.example.composetutorial.data.SampleData

enum class ComposeTutorialScreen() {
    Conversations,
    Conversation
}


@Composable
fun ComposeTutorialApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ComposeTutorialScreen.Conversation.name
        ) {
        composable(route = ComposeTutorialScreen.Conversations.name){
            ConversationsScreen(SampleData.conversationSample)
        }
        composable(route = ComposeTutorialScreen.Conversation.name){
            ConversationScreen()
        }
    }
}
