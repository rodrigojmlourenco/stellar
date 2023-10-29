package uk.co.stellar.asteroids

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch


@Composable
fun AsteroidFeedScreen(
    viewModel: AsteroidFeedViewModel = viewModel()
) {
    val state = viewModel.state.observeAsState()


}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
fun AsteroidFeedLayout(
    asteroids: List<AsteroidUIModel> = emptyList(),
    isLoading: Boolean = false,
    error: String? = null
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simple Scaffold Screen") },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = { innerPadding ->
            LazyColumn(
                // consume insets as scaffold doesn't do it by default
                modifier = Modifier
                    .consumeWindowInsets(innerPadding)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Cyan),
                contentPadding = innerPadding
            ) {

                items(items = asteroids) {
                    AsteroidCard(asteroid = it)
                }
            }

            error?.let {
                scope.launch {
                    snackbarHostState.showSnackbar(it)
                }
            }

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.3f))
                        .focusable(true),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewAsteroidFeedLayout() {
    MaterialTheme {
        AsteroidFeedLayout(
            listOf(AsteroidUIModel("1", "Asteroid A")),
            false,
            "Oopsie daisy!"
        )
    }
}