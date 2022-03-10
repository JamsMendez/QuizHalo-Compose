package com.jamsmendez.quizhalo.ui.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamsmendez.quizhalo.data.repository.QuestionRepository
import com.jamsmendez.quizhalo.data.repository.Result
import com.jamsmendez.quizhalo.model.QuestionModel
import com.jamsmendez.quizhalo.util.Constants.NUM_QUESTIONS
import com.jamsmendez.quizhalo.util.Constants.TOTAL_TIMER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.navegation.RouteNavigator
import com.jamsmendez.quizhalo.ui.screen.RankingRoute
import com.jamsmendez.quizhalo.ui.screen.RegisterRoute
import com.jamsmendez.quizhalo.util.Labels.ERROR_UNKNOWN

@HiltViewModel
class QuizViewModel
@Inject constructor(
  // @Inject constructor(@ApplicationContext context : Context) ... maybe
  private val context: Context,
  private val questionRepository: QuestionRepository,
  private val routerNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routerNavigator {

  private val _questionListState: MutableState<QuestionListState> =
    mutableStateOf(QuestionListState())
  private val _currentQuestionState: MutableState<QuestionModel> = mutableStateOf(QuestionModel())
  private val _valueTimerDownState: MutableState<Float> = mutableStateOf(1f)

  private var _scorePoints: Int = 0
  private var _points: Int = 0
  private var _questionIndex: Int = -1

  private var _mediaShields: MediaPlayer = MediaPlayer.create(context, R.raw.shields)
  private var _mediaLowShields: MediaPlayer = MediaPlayer.create(context, R.raw.low_shields)

  val currentQuestion: State<QuestionModel> = _currentQuestionState
  val valueTimerDown: State<Float> = _valueTimerDownState

  init {
    _mediaLowShields.isLooping = true

    restartShieldsSound()
  }

  private val _countDownTimer = object : CountDownTimer((1000 * TOTAL_TIMER).toLong(), 1000) {
    override fun onTick(millisUntilFinished: Long) {
      val points = (millisUntilFinished / 1000)

      val value = points.toFloat() / TOTAL_TIMER
      _valueTimerDownState.value = value
      _points = points.toInt()

      if (value <= 0.3f && !_mediaLowShields.isPlaying) _mediaLowShields.start()
    }

    override fun onFinish() {
      exposeUnansweredQuestion()
      restartTimerDown(false)
    }
  }

  private fun restartShieldsSound() {
    if (_mediaShields.isPlaying) _mediaShields.stop()
    _mediaShields = MediaPlayer.create(context, R.raw.shields)
  }

  private fun restartLowShieldsSound() {
    if (_mediaLowShields.isPlaying) _mediaLowShields.stop()
    _mediaLowShields = MediaPlayer.create(context, R.raw.low_shields)
  }

  private fun updateNextQuestion(): Boolean {
    val questions = _questionListState.value.questions
    val size = questions.size
    if (size >= NUM_QUESTIONS) {
      val question = questions[_questionIndex]
      val options = question.options.toMutableList().shuffled()
      question.options = options

      _currentQuestionState.value = question
      return true
    }

    return false
  }

  fun onStartClicked() {
    _questionIndex += 1

    getQuestionList {
      val questions = _questionListState.value.questions.shuffled()
      _questionListState.value = QuestionListState(questions = questions)

      if (updateNextQuestion()) _countDownTimer.start()
    }
  }

  fun onRankingClicked() {
    navigateToRoute(RankingRoute.route)
  }

  fun onOptionSelected(index: Int, isCorrect: Boolean) {
    if (_valueTimerDownState.value < 1f) {
      if (isCorrect) _scorePoints += _points

      exposeQuestionResult(index)
      restartTimerDown(isCorrect)
    }
  }

  private fun goToSignInScreen() {
    val route = RegisterRoute.get(_scorePoints)
    navigateToRoute(route)
  }

  private fun restartTimerDown(isCorrect: Boolean) {
    _countDownTimer.cancel()

    restartLowShieldsSound()

    viewModelScope.launch {
      _valueTimerDownState.value = 1f

      _mediaShields.start()

      val timiMillis: Long = if (isCorrect) (1000 * 2).toLong() else 1000 * 3
      delay(timeMillis = timiMillis)

      restartShieldsSound()

      _questionIndex += 1

      if (_questionIndex < NUM_QUESTIONS) {
        if (updateNextQuestion()) _countDownTimer.start()
      }

      if (_questionIndex == NUM_QUESTIONS) {
        goToSignInScreen()

        // I do not like this ...
        viewModelScope.launch {
          delay(1000);

          _scorePoints = 0
          _points = 0
          _questionIndex = -1

          _valueTimerDownState.value = 1f
          _currentQuestionState.value = QuestionModel()
        }
      }
    }
  }

  private fun exposeQuestionResult(selectedIndex: Int) {
    val question = _currentQuestionState.value

    _currentQuestionState.value = QuestionModel(
      id = question.id,
      content = question.content,
      options = question.options.mapIndexed { index, answer ->
        if (index == selectedIndex) answer.selected = true
        answer
      },
      answered = true
    )
  }

  fun exposeUnansweredQuestion() {
    val question = _currentQuestionState.value

    _currentQuestionState.value = QuestionModel(
      id = question.id,
      content = question.content,
      options = question.options.map { answer ->
        if (!answer.correct) answer.selected = true
        answer
      },
      answered = true
    )
  }

  private fun getQuestionList(done: () -> Unit = {}) {
    questionRepository.getQuestionList().onEach { result ->
      when (result) {
        is Result.Error -> {
          _questionListState.value = QuestionListState(error = result.message ?: ERROR_UNKNOWN)
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
}