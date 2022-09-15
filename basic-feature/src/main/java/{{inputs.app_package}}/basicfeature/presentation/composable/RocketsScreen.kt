package {{inputs.app_package}}.basicfeature.presentation.composable

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import {{inputs.app_package}}.basicfeature.R
import {{inputs.app_package}}.basicfeature.presentation.RocketsEvent
import {{inputs.app_package}}.basicfeature.presentation.RocketsEvent.OpenWebBrowserWithDetails
import {{inputs.app_package}}.basicfeature.presentation.RocketsIntent.RefreshRockets
import {{inputs.app_package}}.basicfeature.presentation.RocketsIntent.RocketClicked
import {{inputs.app_package}}.basicfeature.presentation.RocketsUiState
import {{inputs.app_package}}.basicfeature.presentation.RocketsViewModel
import {{inputs.app_package}}.core.extensions.collectAsStateWithLifecycle
import {{inputs.app_package}}.core.extensions.collectWithLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun RocketsScreen(
    viewModel: RocketsViewModel = hiltViewModel()
) {
    HandleEvents(viewModel.event)

    val scaffoldState = rememberScaffoldState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(uiState.isLoading),
            onRefresh = {
                viewModel.acceptIntent(RefreshRockets)
            }
        ) {
            if (uiState.rockets.isNotEmpty()) {
                RocketsAvailableContent(
                    scaffoldState = scaffoldState,
                    uiState = uiState,
                    onRocketClick = {
                        viewModel.acceptIntent(RocketClicked(it))
                    }
                )
            } else {
                RocketsNotAvailableContent(
                    uiState = uiState
                )
            }
        }
    }
}

@Composable
private fun HandleEvents(events: Flow<RocketsEvent>) {
    val uriHandler = LocalUriHandler.current

    events.collectWithLifecycle {
        when (it) {
            is OpenWebBrowserWithDetails -> {
                uriHandler.openUri(it.uri)
            }
        }
    }
}

@Composable
private fun RocketsAvailableContent(
    scaffoldState: ScaffoldState,
    uiState: RocketsUiState,
    onRocketClick: (String) -> Unit
) {
    if (uiState.isError) {
        val errorMessage = stringResource(R.string.rockets_error_fetching)

        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessage
            )
        }
    }

    RocketsListContent(
        rocketList = uiState.rockets,
        onRocketClick = { onRocketClick(it) }
    )
}

@Composable
private fun RocketsNotAvailableContent(uiState: RocketsUiState) {
    when {
        uiState.isLoading -> RocketsLoadingPlaceholder()
        uiState.isError -> RocketsErrorContent()
    }
}
