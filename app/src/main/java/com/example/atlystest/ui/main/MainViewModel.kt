package com.example.atlystest.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlystest.model.Movie
import com.example.atlystest.network.Resource
import com.example.atlystest.network.UiText
import com.example.atlystest.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _trendingMovies: MutableStateFlow<Resource<List<Movie>>?> = MutableStateFlow(null)
    val trendingMovies = _trendingMovies.asStateFlow()
    private var _movieListCache = emptyList<Movie>()
    var movieListState: MutableState<List<Movie>> = mutableStateOf(_movieListCache)


    init {
        getTrendingMovies()
    }
    fun getTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovies().collectLatest {
                _trendingMovies.emit(it)
                _movieListCache = it.data ?: emptyList()
            }
        }
    }


    fun searchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (query.isEmpty()) {
                movieListState.value = _movieListCache
                _trendingMovies.emit(Resource.Success(_movieListCache))
                return@launch
            }
            val movies = _movieListCache.filter {
                it.title.contains(query, ignoreCase = true)
            }
            if (movies.isNotEmpty()) {
                movieListState.value = movies
                //_trendingMovies.emit(Resource.Success(movies))
            } else {
                _trendingMovies.emit(Resource.Error(UiText.ServerString("No movies found")))
            }
        }
    }


    private var selectedMovie: Movie? = null
    fun setSelectedMovie(movie: Movie) {
        selectedMovie = movie
    }

    fun getSelectedMovie() = selectedMovie
}