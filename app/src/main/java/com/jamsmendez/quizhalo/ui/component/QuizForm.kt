package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jamsmendez.quizhalo.model.QuestionModel

@Composable
fun QuizForm(
  modifier: Modifier = Modifier,
  question: QuestionModel,
  valueTimerDown: Float,
  onOptionSelected: (index: Int, isCorrect: Boolean) -> Unit = { _: Int, _: Boolean -> }
) {
  Column(
    modifier = modifier
  ) {
    Row(
      modifier = Modifier
        .padding(horizontal = 32.dp, vertical = 16.dp)
    ) {
      TimerDown(value = valueTimerDown)
    }
    Spacer(
      modifier = Modifier
        .fillMaxWidth()
        .height(16.dp)
    )
    Question(
      question = question,
      onOptionSelected = onOptionSelected,
    )
  }
}