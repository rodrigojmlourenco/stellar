package uk.co.stellar.asteroids

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun AsteroidCard(
    asteroid: AsteroidUIModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .background(MaterialTheme.colorScheme.background)

    ) {
        Text(
            text = asteroid.name,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
@Preview
internal fun PreviewAsteroidCard(
) {
    MaterialTheme {
        AsteroidCard(AsteroidUIModel("id", "End of Days"))
    }
}