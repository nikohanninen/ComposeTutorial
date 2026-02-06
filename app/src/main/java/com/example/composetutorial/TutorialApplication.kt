package com.example.composetutorial

import android.app.Application
import com.example.composetutorial.data.AppContainer
import com.example.composetutorial.data.AppDataContainer

class TutorialApplication : Application() {


    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}