package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamsmendez.quizhalo.model.ScoreModel
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme

@Composable
fun ScoreItem(
  modifier: Modifier = Modifier,
  score: ScoreModel,
  position: Int = 1,
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp)
      .height(48.dp)
  ) {
    Box(
      modifier =Modifier
        .weight(0.2f)
        .padding(end = 8.dp)
    ) {
      BorderBox {
        Text(
          text = "$position",
          modifier = Modifier,
          color = if (position == 1) Color.Yellow else Color.White,
        )
      }
    }
    Box(
      modifier =Modifier
        .weight(0.5f)
        .padding(end = 8.dp)
    ) {
      BorderBox {
        Text(
          text = score.username,
          modifier = Modifier,
          color = Color.White,
        )
      }
    }
    Box(
      modifier =Modifier
        .weight(0.3f)
        .padding(end = 8.dp)
    ) {
      BorderBox {
        Text(
          text = "${score.points}",
          modifier = Modifier,
          color = Color.White,
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ScoreItemPreview() {
  QuizHaloTheme() {
    val score = ScoreModel(id = "1", username = "JamsMendez", points = 117)
    ScoreItem(Modifier, score)
  }
}