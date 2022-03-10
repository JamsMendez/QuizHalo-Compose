package com.jamsmendez.quizhalo.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.jamsmendez.quizhalo.navegation.NavRoute
import com.jamsmendez.quizhalo.ui.component.Ranking
import com.jamsmendez.quizhalo.ui.viewmodel.RankingViewModel

object RankingRoute: NavRoute<RankingViewModel> {

  override val route = "ranking/"

  @Composable
  override fun viewModel(): RankingViewModel = hiltViewModel()

  @Composable
  override fun Content(viewModel: RankingViewModel) = RankingScreen()
}

@Composable
fun RankingScreen(
  rankingViewModel: RankingViewModel = hiltViewModel()
) {

  val scoresListState by rankingViewModel.scoreListState

  Ranking(
    scores = scoresListState.scores,
    onBackClicked = rankingViewModel::onBackClicked
  )
}