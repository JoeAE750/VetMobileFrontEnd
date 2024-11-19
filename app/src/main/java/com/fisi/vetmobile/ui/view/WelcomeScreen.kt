package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fisi.vetmobile.R
import com.fisi.vetmobile.ui.theme.VetMobileTheme
import com.fisi.vetmobile.ui.theme.provider

@Composable
fun WelcomeScreen(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    //var esEspecialista = false
        Column(
            modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            val imagePainter = painterResource(id = R.drawable.vet_launcher_round)
            Image(
                painter = imagePainter,
                contentDescription = "Icono VetMobile",
                modifier = Modifier.size(100.dp)
            )

            Text(
                text = "VET",
                style = TextStyle(
                    fontSize = 40.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(
                        Font(
                            googleFont = GoogleFont("dm_serif_text"),
                            fontProvider = provider,
                        )
                    ),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF61CD5B),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.4.sp,
                )
            )

            Text(
                text = "Mobile",
                style = TextStyle(
                    fontSize = 36.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(
                        googleFont = GoogleFont("jockey_one"),
                        fontProvider = provider,
                    )),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.36.sp,
                )
            )

            ElevatedButton(
                onClick = onRegisterClick,
                enabled = true
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Registrarse"
                    )
                }
            }

            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 30.dp)
                    .size(width = 200.dp, height = 40.dp)
            ) {
                Text("Login", fontSize = 17.sp)
            }

        }
    }

@Preview
@Composable
fun PreviewLayout() {
    VetMobileTheme(darkTheme = true) {
        WelcomeScreen(onLoginClick = {}, onRegisterClick = {})
    }
}