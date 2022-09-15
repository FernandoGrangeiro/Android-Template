package {{inputs.app_package}}.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import {{inputs.app_package}}.core.navigation.NavigationDestination
import {{inputs.app_package}}.core.navigation.NavigationFactory

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    factories: Set<NavigationFactory>
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Rockets.route,
        modifier = modifier,
    ) {
        factories.forEach {
            it.create(this)
        }
    }
}
