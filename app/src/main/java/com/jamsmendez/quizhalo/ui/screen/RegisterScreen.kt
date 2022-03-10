package com.jamsmendez.quizhalo.ui.screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.jamsmendez.quizhalo.navegation.NavRoute
import com.jamsmendez.quizhalo.navegation.getOrThrow
import com.jamsmendez.quizhalo.ui.component.Register
import com.jamsmendez.quizhalo.ui.viewmodel.RegisterViewModel
import com.jamsmendez.quizhalo.util.Labels.MESSAGE_GOD_JOB

const val KEY_SCORE = "score"

object RegisterRoute: NavRoute<RegisterViewModel> {

  override val route = "register/{$KEY_SCORE}"

  override fun getArguments(): List<NamedNavArgument> = listOf(
    navArgument(KEY_SCORE) { type = NavType.IntType })

  @Composable
  override fun viewModel(): RegisterViewModel = hiltViewModel()


  @Composable
  override fun Content(viewModel: RegisterViewModel) = RegisterScreen()

  /**
   * Returns the route that can be used for navigating to this page.
   */
  fun get(score: Int): String = route.replace("{$KEY_SCORE}", "$score")

  fun getScore(savedStateHandle: SavedStateHandle) =
    savedStateHandle.getOrThrow<Int>(KEY_SCORE)
}

@Composable
fun RegisterScreen(
  registerViewModel: RegisterViewModel = hiltViewModel(),
) {
  val scaffoldState = rememberScaffoldState(
    drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  )

  val scoreState by registerViewModel.scoreState
  val scorePoint by registerViewModel.scorePointsState

  val isRegister = scoreState.score.id.isNotEmpty()
  val hasError = scoreState.error.isNotEmpty()

  if (hasError || isRegister) {
    LaunchedEffect(scaffoldState.snackbarHostState) {
      scaffoldState.snackbarHostState.showSnackbar(
        message = scoreState.error.ifEmpty { MESSAGE_GOD_JOB },
        duration = SnackbarDuration.Short,
        actionLabel = null
      )
    }
  }

  Scaffold(
    modifier = Modifier,
    scaffoldState = scaffoldState
  ) {
    Register(
      scorePoint = scorePoint,
      isRegistered = isRegister,
      onBackClicked = registerViewModel::onBackClicked,
      onSignInClicked = registerViewModel::onSignInClicked
    )
  }
}