package com.example.composetutorial

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.composetutorial.classes.ImageUtils
import com.example.composetutorial.data.Account
import com.example.composetutorial.data.AccountRepository
import kotlinx.coroutines.launch

class AccountViewModel(
    private val accountRepository: AccountRepository,
    private val appContext: Context
) : ViewModel() {

    var accountState by mutableStateOf(
        Account(name = "name", profilePicture = null)
    )
        private set

    var accountsState by mutableStateOf<List<Account>>(emptyList())
        private set


    init {
        viewModelScope.launch {
            accountRepository.getAllAccountsStream().collect { accounts ->
                accountsState = accounts
                if (accounts.isNotEmpty()) {
                    accountState = accounts.first()
                }
            }
        }
    }

    fun updateName(name: String) {
        accountState = accountState.copy(name = name)
    }

    fun updateImage(uri: Uri) {
        val path = ImageUtils.copyImageToAppStorage(appContext, uri)
        accountState = accountState.copy(profilePicture = path)
    }

    fun saveAccount() = viewModelScope.launch {
        accountRepository.insertAccount(accountState)
    }

    fun clearAccount() = viewModelScope.launch {
        accountRepository.deleteAccounts()
    }

    fun getAccount(id: Int) = viewModelScope.launch {
        accountRepository.getAccountStream(id).collect { account ->
            accountState = account
        }
    }
}