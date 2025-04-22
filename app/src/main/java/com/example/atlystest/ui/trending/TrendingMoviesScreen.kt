package com.example.atlystest.ui.trending

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.atlystest.R
import com.example.atlystest.model.Movie

@Composable
fun TrendingMoviesScreen(
    data: List<Movie> = emptyList(),
    onSearch: (String) -> Unit = {},
    onSelect: (Movie) -> Unit = {},
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SearchComposeView(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            onValueChange = {
                onSearch(it)
            }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            items(data) {
                MovieItem(
                    movie = it,
                    selectedMovie = {
                        onSelect(it)
                    })
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(movie: Movie, selectedMovie: (Movie) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {
                selectedMovie(movie)
            }
    ) {
        GlideImage(
            modifier = Modifier.size(172.dp).clip(RoundedCornerShape(6.dp)),
            contentScale = ContentScale.Crop,
            model = stringResource(R.string.glide_image_path, movie.posterPath.toString()),
            contentDescription = movie.title
        )
        Text(
            text = movie.title,
            modifier = Modifier.padding(top = 10.dp),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp
            )
        )
    }
}

@Composable
fun MovieList(movies: List<Movie>) {

}

@Preview
@Composable
private fun PreviewTrendingMoviesScreen() {
    TrendingMoviesScreen()
}