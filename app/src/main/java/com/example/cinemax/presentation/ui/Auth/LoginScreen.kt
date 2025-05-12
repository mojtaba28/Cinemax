package com.example.cinemax.presentation.ui.Auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cinemax.R
import com.example.cinemax.navigation.Screen
import com.example.cinemax.presentation.components.AppSnackbar
import com.example.cinemax.presentation.components.LoadingIndicator
import com.example.cinemax.presentation.components.MessageType
import com.example.cinemax.presentation.state.ResponseState
import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import com.example.cinemax.presentation.ui.theme.PrimaryColor
import com.example.cinemax.presentation.ui.theme.SecondaryColor
import com.example.cinemax.presentation.ui.theme.TextSecondary
import com.example.cinemax.presentation.viewmodel.AuthViewModel


@Composable
fun LoginScreen(navController: NavController,viewModel: AuthViewModel= hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val scope= rememberCoroutineScope()
    val snackbarHostState= remember { SnackbarHostState() }
    var messageType by remember { mutableStateOf(MessageType.INFO) }


    val uiState=viewModel.state

    LaunchedEffect(uiState) {
        if (uiState is ResponseState.Success){
            navController.navigate(Screen.Splash.route) {
                popUpTo(Screen.Login.route) {inclusive=true}
            }
        }
    }

    Scaffold( snackbarHost = {
        SnackbarHost(
            hostState = snackbarHostState
        ) { data ->
            AppSnackbar(
                snackbarData = data,
                messageType = messageType
            )
        }
    },
        containerColor = PrimaryColor) {paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryColor)
                .padding(horizontal = 24.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(stringResource(R.string.login), style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(40.dp))

            Text("Hi, Mojtaba", style = MaterialTheme.typography.titleLarge)
            Text(
                stringResource(R.string.welcome_back_please_enter_your_details),
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.email_address), color = TextSecondary) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = PrimaryColor,
                    unfocusedContainerColor = PrimaryColor,
                    focusedIndicatorColor = TextSecondary,
                    unfocusedIndicatorColor = TextSecondary,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.password), color = TextSecondary) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = PrimaryColor,
                    unfocusedContainerColor = PrimaryColor,
                    focusedIndicatorColor = TextSecondary,
                    unfocusedIndicatorColor = TextSecondary,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = null, tint = TextSecondary)
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    stringResource(R.string.forgot_password),
                    color = SecondaryColor,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable {

                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.login(email,password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SecondaryColor),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = if (uiState is ResponseState.Loading) "" else stringResource(R.string.login),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                if (uiState is ResponseState.Loading) {
                    LoadingIndicator()
                }

            }

            LaunchedEffect(uiState) {
                if (uiState is ResponseState.Error) {
                    messageType = MessageType.ERROR
                    snackbarHostState.showSnackbar(uiState.message)
                }
            }

        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
fun PreviewLoginScreen() {

    CinemaxTheme {
        LoginScreen(navController = rememberNavController())
    }
}