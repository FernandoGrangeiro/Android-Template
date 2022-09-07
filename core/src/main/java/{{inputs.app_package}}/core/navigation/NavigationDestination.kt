package {{inputs.app_package}}.core.navigation

sealed class NavigationDestination(
    val route: String
) {
    object Rockets : NavigationDestination("rocketsDestination")
}
