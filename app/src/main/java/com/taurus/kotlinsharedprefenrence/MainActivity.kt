package com.taurus.kotlinsharedprefenrence

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.taurus.kotlinsharedprefenrence.extensions.fragmentTransaction


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    fragmentTransaction {
      replace(R.id.fragmentContainer, FirstFragment())
    }

  }
}
