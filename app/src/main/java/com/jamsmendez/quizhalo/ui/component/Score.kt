package com.jamsmendez.quizhalo.ui.component


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamsmendez.quizhalo.ui.theme.HaloTypography
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme


@Composable
fun Score(modifier: Modifier = Modifier, value: Int = 0) {
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center
  ) {
    BorderBox(
      modifier = Modifier.size(150.dp, 75.dp),
    ) {
      Text(
        text = "$value",
        modifier = Modifier,
        Color.White,
        style = HaloTypography.h4
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ScorePreview() {
  QuizHaloTheme() {
    Score(Modifier, 300)
  }
}