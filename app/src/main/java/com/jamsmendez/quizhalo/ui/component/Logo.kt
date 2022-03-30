package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.ui.theme.HaloTypography
import com.jamsmendez.quizhalo.ui.theme.Purple700
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme
import com.jamsmendez.quizhalo.util.Labels

@Composable
fun Logo(
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    Text(
      text = Labels.QUIZ,
      modifier = Modifier,
      Color.White,
      style = HaloTypography.h3
    )
    Text(
      text = Labels.HALO,
      modifier = Modifier,
      color = Color.White,
      fontSize = 76.sp,
      fontWeight = FontWeight.Bold,
      fontFamily = FontFamily(
        Font(R.font.halo),
        Font(R.font.halo, FontWeight.Bold)
      )
    )
  }
}


@Preview(showBackground = false)
@Composable
fun LogoPreview() {
  QuizHaloTheme {
    Logo()
  }
}