package com.jamsmendez.quizhalo.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamsmendez.quizhalo.data.repository.Result
import com.jamsmendez.quizhalo.data.repository.ScoreRepository
import com.jamsmendez.quizhalo.model.ScoreModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
  @Inject
  constructor(
    private val scoreRepository: ScoreRepository
  ): ViewModel() {

  private val _scoreState: MutableState<ScoreState> = mutableStateOf(ScoreState())
  private val _scoreSending: MutableState<Boolean> = mutableStateOf(false)


  val scoreState: State<ScoreState> = _scoreState
  val scoreSending: State<Boolean> = _scoreSending

  fun scorePoints(username: String, points: Int, done: (isSuccess: Boolean) -> Unit = {}) {
    val score = ScoreModel(
      id = UUID.randomUUID().toString(),
      username = username,
      points = points
    )

    addScore(score, done)
  }

  fun updateScoreSending(isSending: Boolean) {
    _scoreSending.value = isSending
  }

  private fun addScore(score: ScoreModel, done: (isSuccess: Boolean) -> Unit = {}) {
    scoreRepository.addScore(score).onEach { result ->
      when(result) {
        is Result.Error -> {
          _scoreState.value = ScoreState(error = result.message ?: "Error inesperado")
          done(false)
        }
        is Result.Loading -> {
          _scoreState.value = ScoreState(isLoading = true)
        }
        is Result.Success -> {
          _scoreState.value = ScoreState(score = result.data ?: ScoreModel())
          done(true)
        }
      }
    }.launchIn(viewModelScope)
  }

  /*

        is Result.Error -> {
          _scoreState.value = ScoreState(error = result.message ?: "Error inesperado")
        }
        is Result.Loading -> {
          _scoreState.value = ScoreState(isLoading = true)
        }
        is Result.Success -> {
          _scoreState.value = ScoreState(score = result.data ?: ScoreModel())
        }

   private fun getQuestionList(done: () -> Unit = {}) {
    questionRepository.getQuestionList().onEach { result ->
      when(result) {
        is Result.Error -> {
          _questionListState.value = QuestionListState(error = result.message ?: "Error inesperado")
        }
        is Result.Loading -> {
          _questionListState.value = QuestionListState(isLoading = true)
        }
        is Result.Success -> {
          _questionListState.value = QuestionListState(questions = result.data ?: emptyList())
          done()
        }
      }
    }.launchIn(viewModelScope)
  }
   */
}