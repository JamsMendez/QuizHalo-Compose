package com.jamsmendez.quizhalo.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jamsmendez.quizhalo.ui.screen.*

@Composable
fun SetupNavGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = QuizRoute.route,
  ) {
    QuizRoute.composable(this, navHostController = navController)
    RankingRoute.composable(this, navHostController = navController)
    RegisterRoute.composable(this, navHostController = navController)
  }
}

@Composable
fun SetupNavGraph2(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = Screen.Quiz.route,
  ) {
    composable(route = Screen.Quiz.route) {
      //QuizScreen(navController = navController)
    }
    composable(route = Screen.Scores.route) {
      //ScoresScreen(navController = navController)
      navController.popBackStack()
    }
    composable(
      route = "${Screen.SignIn.route}/{score}",
      arguments = listOf(navArgument("score") { type = NavType.IntType })
    ) {
      it.arguments?.getInt("score")?.let { score ->
        RegisterScreen(
        )
      }
    }
  }
}