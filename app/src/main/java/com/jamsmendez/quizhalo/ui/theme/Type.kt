package com.jamsmendez.quizhalo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jamsmendez.quizhalo.R

private val HaloInfinite = FontFamily(
  Font(R.font.rajdhani_regular),
  Font(R.font.rajdhani_bold, FontWeight.Bold),
  Font(R.font.rajdhani_medium, FontWeight.Medium),
  Font(R.font.rajdhani_light, FontWeight.Light),
  Font(R.font.rajdhani_semibold, FontWeight.SemiBold)
)

val Halo = FontFamily(
  Font(R.font.halo),
)

val HaloTypography = Typography(
  h1 = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Light,
    fontSize = 100.sp
  ),
  h2 = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Light,
    fontSize = 62.sp
  ),
  h3 = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Normal,
    fontSize = 50.sp
  ),
  h4 = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Normal,
    fontSize = 35.sp
  ),
  h5 = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Normal,
    fontSize = 25.sp
  ),
  h6 = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Medium,
    fontSize = 21.sp
  ),
  subtitle1 = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp
  ),
  subtitle2 = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp
  ),
  body1 = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp
  ),
  body2 = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp
  ),
  button = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp
  ),
  caption = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
  ),
  overline = TextStyle(
    fontFamily = HaloInfinite,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
  )
)