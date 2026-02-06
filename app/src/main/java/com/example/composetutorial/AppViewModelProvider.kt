package com.example.composetutorial

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = tutorialApplication()
            AccountViewModel(tutorialApplication().container.accountRepository, application.applicationContext)
        }
    }
}

fun CreationExtras.tutorialApplication(): TutorialApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TutorialApplication)
