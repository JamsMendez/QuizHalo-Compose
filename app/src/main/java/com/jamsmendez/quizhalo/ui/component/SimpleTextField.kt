package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.jamsmendez.quizhalo.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleTextField(
  text: String = "",
  onChangeText: (value: String) -> Unit = {},
  onSuccess: () -> Unit = {}
) {
  val keyboardController = LocalSoftwareKeyboardController.current

  OutlinedTextField(
    value = text,
    onValueChange = onChangeText,
    modifier = Modifier
      .fillMaxWidth()
      .background(Color.Transparent), leadingIcon = {
      Icon(
        painter = painterResource(id = R.drawable.claimant),
        contentDescription = "Icon username",
        modifier = Modifier
          .padding(8.dp)
          .size(24.dp),
        tint = Color.White.copy(alpha = 0.75f),
      )
    },
    keyboardOptions = KeyboardOptions(
      imeAction = ImeAction.Send
    ),
    keyboardActions = KeyboardActions(
      onSend = {
        if (text.trim().isNotEmpty()) {
          keyboardController?.hide()
          onSuccess()
        }
      }
    ),
    singleLine = true,
    colors = TextFieldDefaults.outlinedTextFieldColors(
      textColor = Color.White,
      cursorColor = Color.White,
      unfocusedBorderColor = Color.White,
      focusedBorderColor = Color.White
    )
  )
}