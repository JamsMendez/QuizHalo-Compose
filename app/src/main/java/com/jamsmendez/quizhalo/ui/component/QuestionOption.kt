package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamsmendez.quizhalo.model.AnswerModel
import com.jamsmendez.quizhalo.ui.theme.HaloTypography
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme

@Composable
fun QuestionOption(
  modifier: Modifier = Modifier,
  answer: AnswerModel,
  selected: Boolean = false,
  expose: Boolean = false,
  onOptionSelected: (isCorrect: Boolean) -> Unit = {}
) {
  Box(
    modifier = Modifier
      .padding(16.dp, 4.dp)
      .clickable {
        onOptionSelected(answer.correct)
      }
  ) {
    Row(
      modifier = modifier
        .fillMaxWidth()
        .background(
          if (answer.correct && (expose || selected))
            Color.Green.copy(alpha = 0.45f)
          else if (!answer.correct && selected)
            Color.Red.copy(alpha = 0.45f)
          else
            Color.White.copy(alpha = 0.45f)
        )
        .padding(8.dp)
    ) {
      Text(
        text = answer.content,
        modifier = Modifier,
        color = Color.White,
        style = HaloTypography.subtitle1
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun QuestionOptionPreview() {
  QuizHaloTheme {
    QuestionOption(
      modifier = Modifier.background(Color.Black),
      answer = AnswerModel(content = "Respuesta", correct = false, selected = false),
      selected = true,
      expose = false,
    )
  }
}