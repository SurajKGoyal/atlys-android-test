package com.example.atlystest.repository

import com.example.atlystest.R
import com.example.atlystest.db.MovieDao
import com.example.atlystest.model.Movie
import com.example.atlystest.network.Api
import com.example.atlystest.network.NoInternetException
import com.example.atlystest.network.Resource
import com.example.atlystest.network.UiText
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val api: Api
) {

    fun getMovies() = flow<Resource<List<Movie>>> {
        try {
            emit(Resource.Loading())
            val result = api.getTrendingMovies(apiKey = "117e2f06b24862dd66462a956a0805cd")
            if (result.isNotEmpty()) {
                movieDao.insertMovies(result)
                emit(Resource.Success(result))
            }
        } catch (e: Exception) {
            if (e is NoInternetException) {
                val movies = movieDao.getMovies()
                if (movies.isEmpty()) {
                    emit(Resource.Error(UiText.ServerString(e.message)))
                }else{
                    emit(Resource.Success(movies))
                }
            } else {
                emit(Resource.Error(UiText.LocalString(R.string.something_went_wrong)))
            }
        }
    }
}