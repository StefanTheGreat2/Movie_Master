package com.example.moviemaster.ui.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviemaster.ui.theme.transparentBlack

@Composable
fun PopBackButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp)
            .clip(CircleShape)
            .background(transparentBlack.copy(0.7f))
            .size(45.dp),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .clickable {onClick.invoke() }
                .size(35.dp),
            tint = Color.LightGray
        )

    }
}

@Composable
fun FavoritesButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable {
                onClick.invoke()
            }
            .size(65.dp)
            .clip(CircleShape)
            .padding(10.dp)
            .background(transparentBlack.copy(0.7f, blue = 0.2f), shape = CircleShape),
        contentAlignment = Alignment.Center) {

        Icon(
            modifier = Modifier
                .size(35.dp),
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorites",
            tint = Color.Yellow
        )
    }
}
