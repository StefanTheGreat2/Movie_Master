package com.example.moviemaster.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviemaster.common.resource.Resource
import com.example.moviemaster.data.models.response.MoviePage
import com.example.moviemaster.navigation.Routs
import com.example.moviemaster.ui.ImageLoader.MovieImage
import com.example.moviemaster.ui.theme.transparentBlack
import com.example.moviemaster.ui.theme.transparentWhite

@Composable
fun HomeScreen(mainViewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val data: Resource<MoviePage>? by mainViewModel.movies.observeAsState()
    val movies = data?.data?.results
    val visibleItemIndex = remember {
        mutableStateOf(0)
    }
    val topBar = remember {
        mutableStateOf(false)
    }
    val showMovieDetails = remember {
        mutableStateOf(false)
    }
    val movieIndex = remember {
        mutableStateOf(0)
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            val state = rememberLazyGridState()
            LazyVerticalGrid(state = state, columns = GridCells.Fixed(2), content = {


                if (state.firstVisibleItemIndex > visibleItemIndex.value) {
                    topBar.value = true
                }
                if (state.firstVisibleItemIndex < visibleItemIndex.value) {
                    topBar.value = false
                }
                visibleItemIndex.value = state.firstVisibleItemIndex

                if (movies != null) {
                    items(movies.size) { index ->
                        Box(
                            modifier = Modifier
                                .padding(
                                    top = if (index == 0 || index == 1) {
                                        95.dp
                                    } else {
                                        5.dp
                                    }, start = 5.dp, bottom = 5.dp, end = 5.dp
                                )
                                .clickable {
                                    movieIndex.value = index
                                    showMovieDetails.value = true
//                                    val movieId = movie.id
//                                    navController.navigate(route = "details_screen/$movieId")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            MovieImage(movies[index].posterPath)

                        }
                    }
                } else {
                    item {
                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.fillMaxSize(),
                                color = Color.White
                            )
                        }
                    }
                }


            })

        }
        MovieCategories(homeViewModel = mainViewModel, navController, topBar.value)

        if (showMovieDetails.value) {

            MovieDetailsScreen(movies?.get(movieIndex.value)!!, mainViewModel)
            Box(
                modifier = Modifier
                    .padding(top = 20.dp, start = 10.dp)
                    .clip(CircleShape)
                    .background(transparentBlack.copy(0.7f)).size(45.dp), contentAlignment = Alignment.Center

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable { showMovieDetails.value = false }.size(35.dp),
                    tint = Color.LightGray
                )

            }


        }
    }
}


@Composable
fun MovieCategories(homeViewModel: MainViewModel, navController: NavController, collapse: Boolean) {
    if (collapse) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(transparentBlack.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Movie Master", color = Color.White, fontWeight = Bold)
        }
    } else {
        val movieCategories = listOf("Now Playing", "Upcoming", "Popular", "Top Rated", "Favorites")
        Box(
            modifier = Modifier
                .clip(
                    CircleShape.copy(
                        topStart = CornerSize(0.dp),
                        topEnd = CornerSize(0.dp),
                        bottomEnd = CornerSize(25.dp),
                        bottomStart = CornerSize(25.dp)
                    )
                )
                .background(
                    transparentBlack.copy(alpha = 0.6f)
                )
        )
        {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = "Movie Master", color = Color.White, fontWeight = Bold)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Center
            ) {
                movieCategories.forEach { movieCategory ->
                    Button(modifier = Modifier
                        .clip(CircleShape.copy(CornerSize(15.dp)))
                        .padding(20.dp),
                        elevation = ButtonDefaults.buttonElevation(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Black.copy(0.7f)
                        ), border = BorderStroke(1.dp, color = transparentWhite.copy(alpha = 0.6f)),
                        onClick = {
                            when {
                                movieCategory === "Now Playing" -> homeViewModel.nowPlaying()
                                movieCategory === "Popular" -> homeViewModel.popular()
                                movieCategory === "Upcoming" -> homeViewModel.upcoming()
                                movieCategory === "Top Rated" -> homeViewModel.topRated()
                                movieCategory === "Favorites" -> navController.navigate(Routs.Favorites.screen_route)
                            }
                        }) {
                        Text(
                            text = movieCategory, color = Color.White, fontSize = 12.sp
                        )
                    }

                }
            }


        }
    }
}




