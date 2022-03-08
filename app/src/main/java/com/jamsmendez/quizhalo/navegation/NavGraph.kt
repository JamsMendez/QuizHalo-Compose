package com.jamsmendez.quizhalo.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jamsmendez.quizhalo.ui.screen.QuizScreen
import com.jamsmendez.quizhalo.ui.screen.ScoresScreen
import com.jamsmendez.quizhalo.ui.screen.SignInScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = Screen.Quiz.route,
  ) {
    composable(route = Screen.Quiz.route) {
      QuizScreen(navController = navController)
    }
    composable(route = Screen.Scores.route) {
      ScoresScreen(navController = navController)
    }
    composable(
      route = "${Screen.SignIn.route}/{score}",
      arguments = listOf(navArgument("score") { type = NavType.IntType })
    ) {
      it.arguments?.getInt("score")?.let { score ->
        SignInScreen(
          navController = navController,
          points = score
        )
      }
    }
  }
}