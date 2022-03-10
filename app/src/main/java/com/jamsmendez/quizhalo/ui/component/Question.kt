package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.model.AnswerModel
import com.jamsmendez.quizhalo.model.QuestionModel
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme

@Composable
fun Question(
  modifier: Modifier = Modifier,
  question: QuestionModel,
  onOptionSelected: (index: Int, isCorrect: Boolean) -> Unit = { _: Int, _: Boolean -> }
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .wrapContentSize()
  ) {
    Text(
      text = question.content,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 32.dp),
      Color.White, 
      fontSize = 24.sp,
      fontWeight = FontWeight.Bold,
      fontFamily = FontFamily(
        Font(R.font.rajdhani_regular), 
        Font(R.font.rajdhani_regular, FontWeight.Bold)
      ),
      textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(16.dp).fillMaxWidth())
    question.options.forEachIndexed { index, answer ->
      QuestionOption(
        answer = answer,
        selected = answer.selected,
        expose = question.answered,
        onOptionSelected = { isCorrect -> onOptionSelected(index, isCorrect) }
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun QuestionPreview() {
  QuizHaloTheme {
    val question = QuestionModel(
      id = "1",
      content = "¿Cómo se llama la famosa misión de halo 3 en la que los scarabs son los grandes protagonistas?",
      options = listOf(
        AnswerModel("El Arca", false),
        AnswerModel("El Arca", true),
        AnswerModel("El Arca", false),
      )
    )

    Question(
      modifier = Modifier.background(Color.Black),
      question = question
    )
  }
}