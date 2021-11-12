package com.example.roomdbapp

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object AsyncManager {
    val executor: ExecutorService by lazy {
        Executors.newSingleThreadExecutor()
    }

    val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }
}