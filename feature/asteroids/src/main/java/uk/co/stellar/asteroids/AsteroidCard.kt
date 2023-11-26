package uk.co.stellar.asteroids

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun AsteroidCard(
    asteroid: AsteroidUIModel
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (asteroid.isDangerous) {
                MaterialTheme.colorScheme.errorContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

            if (asteroid.isDangerous) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.error)
                        .padding(8.dp)
                ) {
                    Icon(
                        Icons.Rounded.Warning,
                        tint = MaterialTheme.colorScheme.onError,
                        contentDescription = null,
                    )
                    Text(text = stringResource(id = R.string.watch_out_for_this_one), color = MaterialTheme.colorScheme.onError)
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(8.dp)
                ) {
                    Icon(
                        Icons.Rounded.FavoriteBorder,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentDescription = null,
                    )
                    Text(
                        text = stringResource(id = R.string.nothing_to_worry_about),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            Text(
                text = asteroid.name,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(16.dp, 0.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val contentDescription = if (asteroid.distance != null) {
                    stringResource(id = R.string.the_asteroid_is_x_km_away_from_the_earth, asteroid.distance)
                } else {
                    stringResource(id = R.string.the_asteroid_is_at_an_unknown_distance_from_the_earth)
                }
                Image(
                    painter = painterResource(id = R.drawable.planet_earth_800),
                    contentDescription = contentDescription,
                    modifier = Modifier.size(100.dp)
                )

                Column {

                    Divider(
                        color = MaterialTheme.colorScheme.secondary,
                        thickness = 1.dp,
                        modifier = Modifier.width(asteroid.displayDistanceInDps.dp)
                    )
                }

                Column(modifier = Modifier.fillMaxHeight()) {
                    Image(
                        painter = painterResource(id = R.drawable.asteroid),
                        contentDescription = "Something",
                        modifier = Modifier.size(asteroid.displaySizeInDps.dp)
                    )
                    Text(
                        text = asteroid.size,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

            }
        }

    }
}

@Composable
@Preview
internal fun PreviewAsteroidCard(
) {
    MaterialTheme {
        AsteroidCard(
            AsteroidUIModel(
                "id",
                "End of Days",
                "1m",
                "1km",
                1,
                1,
                false
            )
        )
    }
}