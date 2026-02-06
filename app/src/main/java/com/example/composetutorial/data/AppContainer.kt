package com.example.composetutorial.data

import android.content.Context

interface AppContainer {
    val accountRepository: AccountRepository
}


class AppDataContainer(private val context: Context) : AppContainer {
    override val accountRepository: AccountRepository by lazy {
        OfflineAccountRepository(AccountDatabase.getDatabase(context).accountDao())
    }
}