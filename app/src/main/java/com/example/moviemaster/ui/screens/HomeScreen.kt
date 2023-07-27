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
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviemaster.common.resource.Resource
import com.example.moviemaster.data.models.response.MoviePage
import com.example.moviemaster.ui.imageLoader.MovieImage
import com.example.moviemaster.ui.buttons.PopBackButton
import com.example.moviemaster.ui.navigation.Rout
import com.example.moviemaster.ui.theme.transparentBlack
import com.example.moviemaster.ui.theme.transparentWhite

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavController,
    snackBarHostState: SnackbarHostState
) {
    val data: Resource<MoviePage>? by mainViewModel.movies.observeAsState()
    val movies = data?.data?.results
    val visibleItemIndex = remember {
        mutableStateOf(0)
    }
    val collapsed = remember {
        mutableStateOf(false)
    }
    val showMovieDetails = remember {
        mutableStateOf(false)
    }
    val movieIndex = remember {
        mutableStateOf(0)
    }


    ConstraintLayout {
        val (buttonBack, buttonForward) = createRefs()


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
                if (movies != null) {
                    LazyVerticalGrid(state = state, columns = GridCells.Fixed(2), content = {


                        if (state.firstVisibleItemIndex > visibleItemIndex.value) {
                            collapsed.value = true
                        }
                        if (state.firstVisibleItemIndex < visibleItemIndex.value) {
                            collapsed.value = false
                        }
                        visibleItemIndex.value = state.firstVisibleItemIndex

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
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                MovieImage(movies[index].posterPath)

                            }
                        }


                    })
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(200.dp),
                            color = Color.Yellow
                        )
                    }

                }

            }
            TopBarWithCategories(homeViewModel = mainViewModel, navController, collapsed.value)

            if (showMovieDetails.value) {
                MovieDetailsScreen(
                    movies?.get(movieIndex.value)!!,
                    mainViewModel,
                    snackBarHostState
                )

                    PopBackButton {
                        showMovieDetails.value = false
                    }




            }
        }
        if (!showMovieDetails.value&&!collapsed.value) {
            Button(
                onClick = { mainViewModel.nextPage() },
                modifier = Modifier
                    .constrainAs(buttonForward) {
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                    }
                    .clip(CircleShape), colors = ButtonDefaults.buttonColors(Color.Black)) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Forward")
            }
            Button(
                onClick = { mainViewModel.previousPage() },
                modifier = Modifier
                    .constrainAs(buttonBack) {
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                        start.linkTo(parent.start, margin = 10.dp)
                    }
                    .clip(CircleShape), colors = ButtonDefaults.buttonColors(Color.Black)) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }

    }
}


@Composable
fun TopBarWithCategories(
    homeViewModel: MainViewModel,
    navController: NavController,
    collapse: Boolean
) {
    if (collapse) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(transparentBlack.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {

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
                            Color.Black.copy(0.6f)
                        ), border = BorderStroke(1.dp, color = transparentWhite.copy(alpha = 0.5f)),
                        onClick = {
                            when {
                                movieCategory === "Now Playing" -> homeViewModel.nowPlaying()
                                movieCategory === "Popular" -> homeViewModel.popular()
                                movieCategory === "Upcoming" -> homeViewModel.upcoming()
                                movieCategory === "Top Rated" -> homeViewModel.topRated()
                                movieCategory === "Favorites" -> navController.navigate(Rout.Favorites.screen_route)
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




