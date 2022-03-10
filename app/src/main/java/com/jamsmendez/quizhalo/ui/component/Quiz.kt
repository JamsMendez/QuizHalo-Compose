package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.model.AnswerModel
import com.jamsmendez.quizhalo.model.QuestionModel
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme

@Composable
fun Quiz(
  timerValue: Float = 1f,
  hasQuestion: Boolean = false,
  question: QuestionModel = QuestionModel(),
  onStartClicked: () -> Unit = {},
  onRankingClicked: () -> Unit = {},
  onOptionSelected: (index: Int, isCorrect: Boolean) -> Unit = { _: Int, _: Boolean -> },
) {
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

      if (hasQuestion) {
        QuizForm(
          modifier = modifier,
          question = question,
          valueTimerDown = timerValue,
          onOptionSelected = onOptionSelected,
        )

      } else {
        StartForm(
          modifier = modifier,
          onStart = onStartClicked,
          onRankingClicked = onRankingClicked
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun QuizPreview() {
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

    Quiz(
      hasQuestion = true,
      question = question,
    )
  }
}