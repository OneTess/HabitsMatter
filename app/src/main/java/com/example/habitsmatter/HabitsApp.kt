package com.example.habitsmatter

import android.app.Application

class HabitsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}