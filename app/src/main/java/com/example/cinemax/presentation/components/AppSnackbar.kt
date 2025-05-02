package com.example.cinemax.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class MessageType {
    SUCCESS,
    ERROR,
    INFO
}

@Composable
fun AppSnackbar(
    snackbarData: SnackbarData,
    messageType: MessageType
){
    val backgroundColor=when(messageType){
        MessageType.SUCCESS-> Color.Green
        MessageType.ERROR-> Color.Red
        MessageType.INFO-> Color.DarkGray
    }

    Snackbar(
        modifier = Modifier.padding(12.dp)
            .fillMaxWidth()
            .background(color = backgroundColor, shape = SnackbarDefaults.shape)
    ) {

        Text(text = snackbarData.visuals.message)

    }
}