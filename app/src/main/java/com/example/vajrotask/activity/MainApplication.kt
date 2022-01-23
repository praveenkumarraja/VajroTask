package com.example.vajrotask.activity
import android.app.Activity
import android.os.Bundle
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication


class MyApplication : MultiDexApplication() {
    var mInstance: MyApplication? = null

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        //disable screenshot and Video recording all screens
        setupActivityListener()

        MultiDex.install(this)
    }

    private fun setupActivityListener() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                /* activity.window.setFlags(
                     WindowManager.LayoutParams.FLAG_SECURE,
                     WindowManager.LayoutParams.FLAG_SECURE
                 )*/
            }

            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }
}