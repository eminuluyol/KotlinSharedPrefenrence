package com.taurus.kotlinsharedprefenrence

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.kotlinsharedprefenrence.R.string


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val collapsibleCard = findViewById<CollapsibleCard>(R.id.collapsibleCard)
    val htmlTextView = HtmlTextView(this)
    htmlTextView.text = getString(string.dummy_content)
    collapsibleCard.addChildView(htmlTextView)

    val preferences = UserPreferences()
    preferences.allowAnonymousLogging = false
    preferences.userAge = 27
    preferences.emailAccount = "eminuluyol@gmail.com"

    Log.i("StoredData1", preferences.allowAnonymousLogging.toString())
    Log.i("StoredData2", preferences.userAge.toString())
    Log.i("StoredData3", preferences.emailAccount)
    Log.i("StoredData4", preferences.userSalary.toString())
  }
}
