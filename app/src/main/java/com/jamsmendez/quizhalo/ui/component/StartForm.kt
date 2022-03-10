package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme
import com.jamsmendez.quizhalo.util.Labels.BTN_RANKING
import com.jamsmendez.quizhalo.util.Labels.BTN_START

@Composable
fun StartForm(
  modifier: Modifier = Modifier,
  onStart: () -> Unit = {},
  onRankingClicked: () -> Unit = {}
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Logo()
    Spacer(
      modifier = Modifier
        .fillMaxWidth()
        .height(16.dp)
    )
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    ) {
      SimpleButton(
        modifier = Modifier.weight(0.45f),
        text = BTN_START,
        onClick = onStart
      )
      Spacer(
        modifier = Modifier
          .height(16.dp)
          .weight(0.1f)
      )
      SimpleButton(
        modifier = Modifier.weight(0.45f),
        text = BTN_RANKING,
        onClick = onRankingClicked
      )
    }
  }
}


@Composable
@Preview(showBackground = true)
fun StartFormPreview() {
  QuizHaloTheme {
    StartForm(
      modifier = Modifier.background(Color.Black)
    )
  }
}