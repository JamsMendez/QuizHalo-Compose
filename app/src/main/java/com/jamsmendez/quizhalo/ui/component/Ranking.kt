package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.model.ScoreModel
import com.jamsmendez.quizhalo.ui.theme.HaloTypography
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme
import com.jamsmendez.quizhalo.util.Labels

@Composable
fun Ranking(
  scores: List<ScoreModel> = emptyList(),
  onBackClicked: () -> Unit = {},
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
  ) {
    Image(
      painter = painterResource(id = R.drawable.background_scores),
      contentDescription = "Image background in Scores",
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop
    )
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.65f)),
    ) {
      TopAppBar(
        title = {
          Text(
            text = Labels.BTN_RANKING,
            modifier = Modifier
              .fillMaxWidth()
              .padding(end = 64.dp),
            color = Color.White,
            textAlign = TextAlign.Center
          ) },
        modifier = Modifier
          .background(Color.Transparent),
        navigationIcon = {
          IconButton(onClick = onBackClicked) {
            Icon(
              Icons.Filled.ArrowBack,
              contentDescription = null,
              tint = Color.White
            )
          }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
      )
      Text(
        text = Labels.TITLE_SPARTANS,
        modifier = Modifier
          .fillMaxWidth(),
        Color.White,
        style = HaloTypography.h4,
        textAlign = TextAlign.Center
      )

      LazyColumn(
        contentPadding = PaddingValues(
          horizontal = 16.dp,
          vertical = 8.dp
        )
      ) {
        itemsIndexed(scores) { index, score ->
          ScoreItem(score = score, position = index + 1)
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun RankingPreview() {
  QuizHaloTheme {
    Ranking()
  }
}