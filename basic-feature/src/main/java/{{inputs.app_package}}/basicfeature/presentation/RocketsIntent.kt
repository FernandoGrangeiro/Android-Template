package {{inputs.app_package}}.basicfeature.presentation

sealed class RocketsIntent {
    object GetRockets : RocketsIntent()
    object RefreshRockets : RocketsIntent()
    data class RocketClicked(val uri: String) : RocketsIntent()
}
