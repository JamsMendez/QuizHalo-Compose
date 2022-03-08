package com.jamsmendez.quizhalo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.ui.component.ScoreItem
import com.jamsmendez.quizhalo.ui.theme.HaloTypography
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme
import com.jamsmendez.quizhalo.ui.viewmodel.ScoresViewModel
import com.jamsmendez.quizhalo.util.Labels.BTN_RANKING
import com.jamsmendez.quizhalo.util.Labels.TITLE_SPARTANS

@Composable
fun ScoresScreen(
  navController: NavHostController,
  scoreViewModel: ScoresViewModel = hiltViewModel()
) {

  val scoresListState by scoreViewModel.scoreListState

  Box(
    modifier = Modifier
      .fillMaxSize()
  ) {
    Image(
      painter = painterResource(id = R.drawable.background_scores),
      contentDescription = "Image background",
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
            text = BTN_RANKING,
            modifier = Modifier
              .fillMaxWidth()
              .padding(end = 64.dp),
            color = Color.White,
            textAlign = TextAlign.Center
          ) },
        modifier = Modifier
          .background(Color.Transparent),
        navigationIcon = {
          IconButton(
            onClick = {
              navController.popBackStack()
            }
          ) {
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
        text = TITLE_SPARTANS,
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
        itemsIndexed(scoresListState.scores) { index, score ->
          ScoreItem(score = score, position = index + 1)
        }
      }
    }
  }
}