package com.example.atlystest.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.atlystest.R
import com.example.atlystest.network.Resource
import com.example.atlystest.ui.Routes
import com.example.atlystest.ui.detail.MovieDetailsScreen
import com.example.atlystest.ui.theme.AtlysTestTheme
import com.example.atlystest.ui.trending.TrendingMoviesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val uiState = viewModel.trendingMovies.collectAsStateWithLifecycle()

            when (uiState.value) {
                is Resource.Error -> {
                    navController.navigate(Routes.ErrorScreen.route)
                }

                is Resource.Success -> {
                    navController.navigate(Routes.TrendingMoviesScreen.route)
                }

                is Resource.Loading -> {
                    navController.navigate(Routes.LoadingScreen.route)
                }

                else -> Unit
            }
            AtlysTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.LoadingScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.TrendingMoviesScreen.route) {
                            TrendingMoviesScreen(
                                data = viewModel.movieListState.value,
                                onSelect = {
                                    viewModel.setSelectedMovie(it)
                                    navController.navigate(Routes.MovieDetailsScreen.route)
                                },
                                onSearch = {
                                    viewModel.searchMovies(it)
                                }
                            )
                        }
                        composable(Routes.MovieDetailsScreen.route) {
                            MovieDetailsScreen(
                                data = viewModel.getSelectedMovie(),
                                onBackPressed = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(Routes.ErrorScreen.route) {
                            ErrorScreen(
                                message = viewModel.trendingMovies.value?.message?.get(LocalContext.current)
                                    ?: stringResource(R.string.something_went_wrong)
                            ) {
                                viewModel.getTrendingMovies()
                            }
                        }
                        composable(Routes.LoadingScreen.route) {
                            LoadingScreen(modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}