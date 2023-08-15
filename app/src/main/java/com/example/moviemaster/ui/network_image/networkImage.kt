package com.example.moviemaster.ui.network_image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.moviemaster.data.remote.Api

    @Composable
    fun NetworkImage(posterPath: String, imageLoader: ImageLoader) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape.copy(CornerSize(25.dp)))
        ) {
            AsyncImage(
                model = Api.getPosterPath(posterPath),
                contentDescription = "",
                imageLoader = imageLoader,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }

