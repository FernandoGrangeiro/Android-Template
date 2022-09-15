package {{inputs.app_package}}.basicfeature.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import {{inputs.app_package}}.basicfeature.presentation.composable.RocketsScreen
import {{inputs.app_package}}.core.navigation.NavigationDestination.Rockets
import {{inputs.app_package}}.core.navigation.NavigationFactory
import javax.inject.Inject

class RocketsNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable(Rockets.route) {
            RocketsScreen()
        }
    }
}
