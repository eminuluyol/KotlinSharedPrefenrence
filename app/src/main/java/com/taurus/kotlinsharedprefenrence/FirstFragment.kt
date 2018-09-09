package com.taurus.kotlinsharedprefenrence

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class FirstFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_first, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val collapsibleCard = view.findViewById<CollapsibleCard>(R.id.collapsible_card)
    val htmlTextView = HtmlTextView(context!!)
    htmlTextView.text = "Demo Demo Demo"
    collapsibleCard.addChildView(htmlTextView)

    val preferences = UserPreferences()
    preferences.allowAnonymousLogging = false
    preferences.userAge = 27
    preferences.emailAccount = "eminuluyol@gmail.com"

    Log.i("Stored data1", preferences.allowAnonymousLogging.toString())
    Log.i("Stored data2", preferences.userAge.toString())
    Log.i("Stored data3", preferences.emailAccount)
    Log.i("Stored data4", preferences.userSalary.toString())
  }

}