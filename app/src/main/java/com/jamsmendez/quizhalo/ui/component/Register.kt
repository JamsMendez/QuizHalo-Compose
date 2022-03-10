package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jamsmendez.quizhalo.R
import com.jamsmendez.quizhalo.ui.theme.HaloTypography
import com.jamsmendez.quizhalo.util.Labels

@Composable
fun Register(
  scorePoint: Int,
  isRegistered: Boolean,
  onBackClicked: () -> Unit = {},
  onSignInClicked: (username: String) -> Unit = {}
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

      if (isRegistered) {
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
          text = Labels.YOUR_SCORE,
          modifier = Modifier
            .fillMaxWidth(),
          color = Color.White,
          style = HaloTypography.h3,
          textAlign = TextAlign.Center
        )
        Spacer(
          modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
        )
        Score(value = scorePoint)
        Spacer(
          modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
        )

        if (!isRegistered) {
          SignInForm(
            Modifier.fillMaxWidth(),
            onSignInClicked = onSignInClicked
          )
          Spacer(
            modifier = Modifier
              .fillMaxWidth()
              .height(16.dp)
          )
        }

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ) {
          SimpleButton(
            modifier = Modifier.fillMaxWidth(),
            text = Labels.BTN_PLAY_AGAIN,
            lightMode = true,
            onClick = onBackClicked
          )
        }
      }
    }
  }
}