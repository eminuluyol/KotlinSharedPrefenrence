package com.taurus.kotlinsharedprefenrence

import android.app.Application


class DemoApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    BaseSharedPreferences.init(applicationContext)
  }
}