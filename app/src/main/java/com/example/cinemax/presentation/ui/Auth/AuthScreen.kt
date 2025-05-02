package com.example.cinemax.presentation.ui.Auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cinemax.R
import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import com.example.cinemax.presentation.ui.theme.PrimaryColor
import com.example.cinemax.presentation.ui.theme.TextSecondary
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.stringResource
import com.example.cinemax.presentation.ui.theme.SecondaryColor

@Composable
fun AuthScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Icon(
            painter = painterResource(id = R.drawable.tv_ic), // آیکون تلویزیون
            contentDescription = null,
            tint = SecondaryColor,
            modifier = Modifier.size(72.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            stringResource(R.string.cinemax), fontSize = 28.sp,
            color = Color.White, fontWeight = FontWeight.Bold)
        Text(
            "Enter your registered\nPhone Number to Sign Up",
            fontSize = 14.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("signup") },
            colors = ButtonDefaults.buttonColors(containerColor = SecondaryColor),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("Sign Up", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text("I already have an account? ", color = TextSecondary, fontSize = 14.sp)
            Text(
                text = "Login",
                color = SecondaryColor,
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Divider(color = TextSecondary.copy(alpha = 0.3f), thickness = 1.dp)
        Text("Or Sign up with", color = TextSecondary, fontSize = 12.sp)
        Divider(color = TextSecondary.copy(alpha = 0.3f), thickness = 1.dp)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SocialButton(R.drawable.google_ic)
            SocialButton(R.drawable.apple_ic)
            SocialButton(R.drawable.facebook_ic)
        }
    }
}

@Composable
fun SocialButton(iconRes: Int) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .background(Color.Transparent, shape = CircleShape)
            .padding(7.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(35.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
fun PreviewAuthScreen() {
    CinemaxTheme {
        AuthScreen(navController = rememberNavController())
    }
}
