package com.taurus.kotlinsharedprefenrence


class UserPreferences : BaseSharedPreferences() {
  var emailAccount by stringPref()
  var allowAnonymousLogging by booleanPref()
  var userAge by intPref()
  var userSalary by longPref(defaultValue = 100000000)
}