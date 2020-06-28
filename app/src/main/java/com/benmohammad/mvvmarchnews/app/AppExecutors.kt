package com.benmohammad.mvvmarchnews.app

import android.os.Handler
import android.os.Looper
import java.lang.reflect.Executable
import java.util.concurrent.Executor
import javax.inject.Singleton

@Singleton
open class AppExecutors(
    private val diskIO: Executor,
    private val networkIO: Executor,
    private val mainThread: Executor
) {

    fun diskIO() = diskIO

    fun networkIO() = networkIO

    fun mainThread() = mainThread

    private class MainThreadExecutor: Executor {
        private val mainThreadHandler =  Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }

    }}