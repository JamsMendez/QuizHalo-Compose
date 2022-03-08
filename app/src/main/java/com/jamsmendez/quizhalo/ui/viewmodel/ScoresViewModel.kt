package com.jamsmendez.quizhalo.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamsmendez.quizhalo.data.repository.Result
import com.jamsmendez.quizhalo.data.repository.ScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ScoresViewModel
  @Inject constructor(
    private val scoreRepository: ScoreRepository
): ViewModel() {
  private val _scoreListState: MutableState<ScoreListState> = mutableStateOf(ScoreListState())

  val scoreListState: State<ScoreListState> = _scoreListState

  init {
    getScoreList()
  }

  private fun getScoreList() {
    scoreRepository.getScoreList().onEach { result ->
      when(result) {
        is Result.Error -> {
          _scoreListState.value = ScoreListState(error = result.message ?: "Error inesperado")
        }
        is Result.Loading -> {
          _scoreListState.value = ScoreListState(isLoading = true)
        }
        is Result.Success -> {
          _scoreListState.value = ScoreListState(scores = result.data ?: emptyList())
        }
      }
    }.launchIn(viewModelScope)
  }
}