package {{inputs.app_package}}.basicfeature.domain.usecase

import {{inputs.app_package}}.basicfeature.domain.model.Rocket
import {{inputs.app_package}}.basicfeature.domain.repository.RocketRepository
import {{inputs.app_package}}.core.extensions.resultOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

typealias GetRocketsUseCase =
    @JvmSuppressWildcards suspend () -> Flow<Result<List<Rocket>>>

fun getRockets(
    rocketRepository: RocketRepository
): Flow<Result<List<Rocket>>> = rocketRepository
    .getRockets()
    .map {
        resultOf { it }
    }
