package com.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


class LifecycleObserverHandler : DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        println("TAG == ${"onResume"}")

    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        println("TAG == ${"onPause"}")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        println("TAG == ${"onStop"}")

    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        println("TAG == ${"onCreate"}")

    }

    override fun onStart(owner: LifecycleOwner) {
        println("TAG == ${"onStart"}")

    }

}