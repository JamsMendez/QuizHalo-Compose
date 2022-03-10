package com.jamsmendez.quizhalo.ui.screen

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.jamsmendez.quizhalo.navegation.NavRoute
import com.jamsmendez.quizhalo.ui.component.Quiz
import com.jamsmendez.quizhalo.ui.viewmodel.QuizViewModel

object QuizRoute: NavRoute<QuizViewModel> {

  override val route = "quiz/"

  @Composable
  override fun viewModel(): QuizViewModel = hiltViewModel()

  @Composable
  override fun Content(viewModel: QuizViewModel) = QuizScreen()
}

@Composable
fun QuizScreen(
  quizViewModel: QuizViewModel = hiltViewModel(),
) {
  val valueTimerDown = quizViewModel.valueTimerDown
  val currentQuestion = quizViewModel.currentQuestion

  val question = currentQuestion.value

  Quiz(
    timerValue = valueTimerDown.value,
    hasQuestion = question.id.isNotEmpty(),
    question = question,
    onStartClicked = quizViewModel::onStartClicked,
    onRankingClicked = quizViewModel::onRankingClicked,
    onOptionSelected = quizViewModel::onOptionSelected,
  )
}