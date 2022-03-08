package com.jamsmendez.quizhalo.navegation

sealed class Screen(val route: String) {
  object Quiz: Screen(route = "quiz_screen")
  object SignIn: Screen(route = "sign_in_screen")
  object Scores: Screen(route = "scores_screen")
}