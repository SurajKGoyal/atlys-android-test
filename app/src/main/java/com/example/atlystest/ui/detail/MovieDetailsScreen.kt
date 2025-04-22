package com.example.atlystest.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieDetailsScreen(
    data: Movie? = null,
    onBackPressed: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "back",
            tint = Color.Gray,
            modifier = Modifier.clickable {
                onBackPressed()
            }
        )

        GlideImage(
            model = stringResource(R.string.glide_image_path, data?.posterPath.toString()),
            contentDescription = data?.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(vertical = 8.dp).size(360.dp).clip(RoundedCornerShape(6.dp))
        )

        Text(
            text = data?.title ?: "",
            modifier = Modifier.padding(vertical = 16.dp),
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                lineHeight = 32.sp
            )
        )

        Text(
            text = data?.overview ?: "",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            ),
        )

    }

}

@Preview
@Composable
private fun PreviewMovieDetailsScreen() {
    MovieDetailsScreen(
        data = Movie(
            id = 1,
            title = "Marvels",
            overview = "dhfiusdhfiu sahdfiush isnd asnjdn nsdjn  sndnds dn sndis dnsind sdnsi",
            posterPath = ""
        ),
        onBackPressed = {}
    )
}