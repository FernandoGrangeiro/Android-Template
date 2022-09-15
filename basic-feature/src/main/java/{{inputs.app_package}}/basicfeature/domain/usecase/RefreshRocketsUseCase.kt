package {{inputs.app_package}}.basicfeature.domain.usecase

import {{inputs.app_package}}.basicfeature.domain.repository.RocketRepository
import {{inputs.app_package}}.core.extensions.resultOf

typealias RefreshRocketsUseCase =
    @JvmSuppressWildcards suspend () -> Result<Unit>

suspend fun refreshRockets(
    rocketRepository: RocketRepository
): Result<Unit> = resultOf {
    rocketRepository.refreshRockets()
}
