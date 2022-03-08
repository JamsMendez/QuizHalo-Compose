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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.model.AnswerModel

@Composable
fun QuestionOption(
  modifier: Modifier = Modifier,
  answer: AnswerModel,
  selected: Boolean = false,
  expose: Boolean = false,
  onSelectOption: (isCorrect: Boolean) -> Unit = {}
) {
  Box(
    modifier = Modifier
      .padding(16.dp, 4.dp)
      .clickable {
        onSelectOption(answer.correct)
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
        fontSize = 16.sp,
        fontFamily = FontFamily(
          Font(R.font.rajdhani_regular),
          Font(R.font.rajdhani_regular, FontWeight.Bold)
        ),
        fontWeight = FontWeight.Bold
      )
    }
  }
}