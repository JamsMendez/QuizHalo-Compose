package com.jamsmendez.quizhalo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.navegation.Screen
import com.jamsmendez.quizhalo.ui.component.Score
import com.jamsmendez.quizhalo.ui.component.SignInForm
import com.jamsmendez.quizhalo.ui.component.SimpleButton
import com.jamsmendez.quizhalo.ui.theme.HaloTypography
import com.jamsmendez.quizhalo.ui.viewmodel.SignInViewModel
import com.jamsmendez.quizhalo.util.Labels.BTN_PLAY_AGAIN
import com.jamsmendez.quizhalo.util.Labels.MESSAGE_EQUAL_POINTS
import com.jamsmendez.quizhalo.util.Labels.MESSAGE_LESS_POINTS
import com.jamsmendez.quizhalo.util.Labels.YOUR_SCORE
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
  navController: NavHostController,
  signInViewModel: SignInViewModel = hiltViewModel(),
  points: Int
) {
  val scaffoldState = rememberScaffoldState(
    drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  )

  val scope = rememberCoroutineScope()

  val scoreState by signInViewModel.scoreState
  val scoreSending by signInViewModel.scoreSending

  Scaffold(
   modifier = Modifier,
   scaffoldState = scaffoldState
  ) {
    Box(
      modifier = Modifier
    ) {
      Image(
        painter = painterResource(id = R.drawable.background_quiz),
        contentDescription = "Image background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
      )

      ConstraintLayout(
        modifier = Modifier
          .fillMaxSize()
          .background(Color.Black.copy(alpha = 0.5f)),
      ) {
        val (contentRef, imgSignInRef) = createRefs()

        if (scoreSending) {
          Image(
            painter = painterResource(id = R.drawable.sign_in_success),
            contentDescription = "Image SignIn",
            modifier = Modifier
              .fillMaxWidth()
              .constrainAs(imgSignInRef) {
              bottom.linkTo(parent.bottom)
            },
            contentScale = ContentScale.FillWidth
          )
        } else {
          Image(
            painter = painterResource(id = R.drawable.sign_in),
            contentDescription = "Image SignIn",
            modifier = Modifier
              .fillMaxWidth()
              .constrainAs(imgSignInRef) {
              bottom.linkTo(parent.bottom)
            },
            contentScale = ContentScale.FillWidth
          )
        }

        Column(
         modifier = Modifier.constrainAs(contentRef) {
           top.linkTo(parent.top, margin = 32.dp)
         }
        ) {
          Text(
            text = YOUR_SCORE,
            modifier = Modifier
              .fillMaxWidth(),
            color = Color.White,
            style = HaloTypography.h3,
            textAlign = TextAlign.Center
          )
          Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))
          Score(value = points)
          Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))
          if (!scoreSending) {
            SignInForm(
              Modifier.fillMaxWidth()
            ) { username ->
              signInViewModel.scorePoints(username, points) { isSuccess ->
                if (isSuccess) {
                  signInViewModel.updateScoreSending(isSuccess)
                } else {
                  val isOk = scoreState.error == MESSAGE_EQUAL_POINTS || scoreState.error == MESSAGE_LESS_POINTS
                  if (isOk) signInViewModel.updateScoreSending(isOk)
                }

                scope.launch {
                  scaffoldState.snackbarHostState.showSnackbar(
                    message = if (isSuccess) "Buen trabajo soldado" else scoreState.error,
                    duration = SnackbarDuration.Short,
                    actionLabel = null
                  )
                }
              }
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))
          }

          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp)
          ) {
            SimpleButton(
              modifier = Modifier.fillMaxWidth(),
              text = BTN_PLAY_AGAIN,
              lightMode = true,
              onClick = {
                navController.popBackStack()
              }
            )
          }
        }

      }
    }
  }
}