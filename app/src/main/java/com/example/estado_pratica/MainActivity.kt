package com.example.estado_pratica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.estado_pratica.ui.theme.EstadopraticaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val artworks = listOf(
                Artwork("Noite Estrelada", "Vincent Van Gogh", 1889, R.drawable.img),
                Artwork("????????", "Ishida Sui", 2022, R.drawable.img_1)
            )
            ArtGallery(artworks)
        }
    }
}


@Composable
fun ArtworkDisplay(artwork: Artwork, onPreviousClick: () -> Unit, onNextClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = artwork.imageResource),
            contentDescription = artwork.title,
            modifier = Modifier.size(300.dp)
        )
        Text(text = artwork.title, style = MaterialTheme.typography.bodyMedium)
        Text(text = "${artwork.artist} (${artwork.year})", style = MaterialTheme.typography.bodyMedium)
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Button(onClick = onPreviousClick) {
                Text(text = "Previous")
            }
            Button(onClick = onNextClick) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun ArtGallery(artworks: List<Artwork>) {
    var currentIndex by remember { mutableIntStateOf(0) }

    val currentArtwork = artworks[currentIndex]

    ArtworkDisplay(
        artwork = currentArtwork,
        onPreviousClick = {
            if (currentIndex > 0) currentIndex--
        },
        onNextClick = {
            if (currentIndex < artworks.size - 1) currentIndex++
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewArtworkDisplay() {
    val sampleArtwork = Artwork(
        title = "Sailing Under the Bridge",
        artist = "Kat Kuan",
        year = 2017,
        imageResource = R.drawable.img
    )

    ArtworkDisplay(
        artwork = sampleArtwork,
        onPreviousClick = {},
        onNextClick = {}
    )
}



data class Artwork(
    val title: String,
    val artist: String,
    val year: Int,
    val imageResource: Int
)