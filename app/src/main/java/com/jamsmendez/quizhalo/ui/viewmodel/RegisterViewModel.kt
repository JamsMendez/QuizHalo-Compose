package com.jamsmendez.quizhalo.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamsmendez.quizhalo.data.repository.Result
import com.jamsmendez.quizhalo.data.repository.ScoreRepository
import com.jamsmendez.quizhalo.model.ScoreModel
import com.jamsmendez.quizhalo.navegation.RouteNavigator
import com.jamsmendez.quizhalo.ui.screen.RegisterRoute
import com.jamsmendez.quizhalo.util.Labels.ERROR_UNKNOWN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
  @Inject
  constructor(
    private val scoreRepository: ScoreRepository,
    private val routerNavigator: RouteNavigator,
    savedStateHandle: SavedStateHandle,
  ): ViewModel(), RouteNavigator by routerNavigator {

  private val _scorePoints = RegisterRoute.getScore(savedStateHandle)

  private val _scoreState: MutableState<ScoreState> = mutableStateOf(ScoreState())
  private val _scorePointsState: MutableState<Int> = mutableStateOf(_scorePoints)

  val scoreState: State<ScoreState> = _scoreState
  val scorePointsState: State<Int> = _scorePointsState

  fun onSignInClicked(username: String) {
    if (username.trim().isEmpty()) return

    val score = ScoreModel(
      id = UUID.randomUUID().toString(),
      username = username,
      points = _scorePointsState.value
    )

    addScore(score)
  }

  fun onBackClicked() {
    popBackStack()
  }

  private fun addScore(score: ScoreModel) {
    scoreRepository.addScore(score).onEach { result ->
      when(result) {
        is Result.Error -> {
          _scoreState.value = ScoreState(
            error = result.message ?: ERROR_UNKNOWN,
            score = result.data ?: ScoreModel()
          )
        }
        is Result.Loading -> {
          _scoreState.value = ScoreState(isLoading = true)
        }
        is Result.Success -> {
          _scoreState.value = ScoreState(score = result.data ?: ScoreModel())
        }
      }
    }.launchIn(viewModelScope)
  }
}