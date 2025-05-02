package com.example.cinemax.presentation.ui.Auth

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
import com.example.cinemax.presentation.state.UiState
import com.example.cinemax.presentation.ui.dimensions.Dimens
import com.example.cinemax.presentation.ui.theme.CinemaxTheme
import com.example.cinemax.presentation.ui.theme.PrimaryColor
import com.example.cinemax.presentation.ui.theme.SecondaryColor
import com.example.cinemax.presentation.ui.theme.TextPrimary
import com.example.cinemax.presentation.ui.theme.TextSecondary
import com.example.cinemax.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SignUpScreen(navController: NavController,viewModel: AuthViewModel= hiltViewModel()) {


    val uiState=viewModel.state
    val snackbarHostState = remember { SnackbarHostState() }
    var messageType by remember { mutableStateOf(MessageType.INFO) }
    LaunchedEffect(uiState) {

        if (uiState is UiState.Success) {

            navController.navigate(Screen.Login.route){
                popUpTo(Screen.SignUp.route) {inclusive=true}
            }
        }


    }

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            ) { data ->
                AppSnackbar(
                    snackbarData = data,
                    messageType = messageType
                )
            }
        },
        containerColor = PrimaryColor
    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
             horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                    )

                }


                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MaterialTheme.typography.titleLarge.copy(color = TextPrimary),
                    textAlign = TextAlign.Center

                )
                Spacer(Modifier.weight(1.4f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Let’s get started", fontSize = Dimens.ExtraLargeText, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text("The latest movies and series are here", fontSize = Dimens.MediumText, color = TextSecondary)

            Spacer(modifier = Modifier.height(34.dp))

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name", color = TextSecondary) },
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
                shape = RoundedCornerShape(Dimens.FiledBoxesCorner)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = TextSecondary) },
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
                shape = RoundedCornerShape(Dimens.FiledBoxesCorner)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { androidx.compose.material3.Text("Password", color = TextSecondary) },
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
                shape = RoundedCornerShape(Dimens.FiledBoxesCorner),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    androidx.compose.material3.IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        androidx.compose.material3.Icon(
                            imageVector = image,
                            contentDescription = null,
                            tint = TextSecondary
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors (
                        checkedColor = SecondaryColor,
                        uncheckedColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                TermsAndPrivacyText()
            }

            Spacer(modifier = Modifier.height(10.dp))

            val errorMessage = stringResource(R.string.fill_the_inputs)
            Button(onClick ={ if (fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && isChecked) {
                viewModel.register(fullName, email, password)

            } else {

                messageType = MessageType.ERROR

                scope.launch {
                    snackbarHostState.showSnackbar(errorMessage)
                }
            }} ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.ButtonHeight),
                shape = RoundedCornerShape(Dimens.FiledBoxesCorner),
                colors = ButtonDefaults.buttonColors(SecondaryColor)
            ) {

                Text( if (uiState is UiState.Loading) "" else stringResource(R.string.sign_up)
                    , style = MaterialTheme.typography.bodyMedium)

                if (uiState is UiState.Loading){
                    LoadingIndicator()
                }

            }

            if (uiState is UiState.Error){
                messageType = MessageType.ERROR
                LaunchedEffect(Unit) {
                    snackbarHostState.showSnackbar(uiState.message)
                }

            }
        }

    }



}

@Composable
fun TermsAndPrivacyText() {
    val annotatedString = buildAnnotatedString {
        append("I agree to the ")
        pushStyle(SpanStyle(color = SecondaryColor, textDecoration = TextDecoration.Underline))
        append("Terms and Services")
        pop()
        append(" and ")
        pushStyle(SpanStyle(color = SecondaryColor, textDecoration = TextDecoration.Underline))
        append("Privacy Policy")
        pop()
    }
    ClickableText(
        text = annotatedString,
        onClick = { /* TODO */ },
        style = LocalTextStyle.current.copy(color = TextSecondary, fontSize = 12.sp)
    )
}


@Preview(showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
fun PreviewSignUpScreen() {
    CinemaxTheme {
        SignUpScreen(navController = rememberNavController())
    }
}