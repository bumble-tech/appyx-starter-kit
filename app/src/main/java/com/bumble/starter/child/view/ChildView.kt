package com.bumble.starter.child.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumble.starter.ui.theme.md_deep_purple_50

@Composable
fun ChildView(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onSwitchNow: (() -> Unit)? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(color = backgroundColor)
            .fillMaxSize()
    ) {
        Text(
            color = textColor,
            text = text,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        onSwitchNow?.let {
            Button(
                onClick = it,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = md_deep_purple_50
                ),
                modifier = Modifier
                    .padding(bottom = 8.dp)
            ) {
                Text("Swap now")
            }
        }
    }

}