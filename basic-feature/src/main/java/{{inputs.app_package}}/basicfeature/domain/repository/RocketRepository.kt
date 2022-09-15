package {{inputs.app_package}}.basicfeature.domain.repository

import {{inputs.app_package}}.basicfeature.domain.model.Rocket
import kotlinx.coroutines.flow.Flow

interface RocketRepository {
    fun getRockets(): Flow<List<Rocket>>
    suspend fun refreshRockets()
}
