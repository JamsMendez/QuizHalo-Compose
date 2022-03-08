package com.jamsmendez.quizhalo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.navegation.Screen
import com.jamsmendez.quizhalo.ui.component.QuizForm
import com.jamsmendez.quizhalo.ui.component.StartForm
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme
import com.jamsmendez.quizhalo.ui.viewmodel.QuizViewModel

@Composable
fun QuizScreen(
  navController: NavHostController,
  quizViewModel: QuizViewModel = hiltViewModel()
) {
  val valueTimerDown = quizViewModel.valueTimerDown
  val currentQuestion = quizViewModel.currentQuestion

  Box(modifier = Modifier){
    Image(
      painter = painterResource(id = R.drawable.background_quiz),
      contentDescription = "Image background",
      modifier = Modifier
        .fillMaxSize(),
      contentScale = ContentScale.FillBounds
    )
    ConstraintLayout(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.5f)),
    ) {

      val (composableRef) = createRefs()
      val modifier = Modifier.constrainAs(composableRef) {
        top.linkTo(parent.top, margin = 16.dp)
        bottom.linkTo(parent.bottom, margin = 16.dp)
      }

      if (currentQuestion.value.options.isNotEmpty()) {
        QuizForm(
          modifier = modifier,
          question = currentQuestion.value,
          valueTimerDown = valueTimerDown.value,
          onSelectOption = { index: Int, isCorrect: Boolean ->
            quizViewModel.onSelectOption(index, isCorrect) { score ->
              val route = "${Screen.SignIn.route}/${score}"
              navController.navigate(route)
            }
          }
        )

      } else {
        StartForm(
          modifier = modifier,
          onStart = { quizViewModel.startTimerDown() },
          onNavToScores = {
            navController.navigate(Screen.Scores.route)
          }
        )
      }
    }
  }
}

@Composable
@Preview(showBackground = true)
fun QuizScreenPreview() {
  QuizHaloTheme {
    //QuizScreen()
  }
}