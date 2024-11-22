package com.fisi.vetmobile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo Image
        val imagePainter = painterResource(id = R.drawable.vet_launcher_round)
        Image(
            painter = imagePainter,
            contentDescription = "Icono VetMobile",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 32.dp)
        )

        // VET Text
        Text(
            text = "VET",
            style = TextStyle(
                fontSize = 40.sp,
                lineHeight = 48.sp,
                fontFamily = FontFamily(
                    Font(
                        googleFont = GoogleFont("dm_serif_text"),
                        fontProvider = provider
                    )
                ),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF61CD5B),
                textAlign = TextAlign.Center,
                letterSpacing = 0.4.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Mobile Text
        Text(
            text = "Mobile",
            style = TextStyle(
                fontSize = 36.sp,
                lineHeight = 44.sp,
                fontFamily = FontFamily(
                    Font(
                        googleFont = GoogleFont("jockey_one"),
                        fontProvider = provider
                    )
                ),
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                letterSpacing = 0.36.sp
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Register Button
        ElevatedButton(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp),
            colors = ButtonDefaults.elevatedButtonColors(Color(0xFF61CD5B))
        ) {
            Text(
                text = "Registrarse",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        // Login Button
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp, vertical = 16.dp),
        ) {
            Text(
                text = "Login",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
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