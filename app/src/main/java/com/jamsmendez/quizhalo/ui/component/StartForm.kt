package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme
import com.jamsmendez.quizhalo.util.Labels.BTN_RANKING
import com.jamsmendez.quizhalo.util.Labels.HALO
import com.jamsmendez.quizhalo.util.Labels.QUIZ
import com.jamsmendez.quizhalo.util.Labels.BTN_START

@Composable
fun StartForm(
  modifier: Modifier = Modifier,
  onStart: () -> Unit = {},
  onNavToScores: () -> Unit = {}
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      Text(
        text = QUIZ,
        modifier = Modifier,
        Color.White,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(
          Font(R.font.rajdhani_regular),
          Font(R.font.rajdhani_regular, FontWeight.Bold)
        ),
      )
      Text(
        text = HALO,
        modifier = Modifier,
        Color.White,
        fontSize = 76.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(
          Font(R.font.halo),
          Font(R.font.halo, FontWeight.Bold)
        )
      )
    }
    Spacer(modifier = Modifier
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
        onClick = { onStart() }
      )
      Spacer(modifier = Modifier
        .height(16.dp)
        .weight(0.1f)
      )
      SimpleButton(
        modifier = Modifier.weight(0.45f),
        text = BTN_RANKING,
        onClick = { onNavToScores() }
      )
    }
  }
}


@Composable
@Preview(showBackground = true)
fun StartFormPreview() {
  QuizHaloTheme {
    StartForm()
  }
}