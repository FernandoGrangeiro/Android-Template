package {{inputs.app_package}}.basicfeature.data.repository

import {{inputs.app_package}}.basicfeature.data.local.dao.RocketDao
import {{inputs.app_package}}.basicfeature.data.mapper.toDomainModel
import {{inputs.app_package}}.basicfeature.data.mapper.toEntityModel
import {{inputs.app_package}}.basicfeature.data.remote.api.RocketApi
import {{inputs.app_package}}.basicfeature.domain.model.Rocket
import {{inputs.app_package}}.basicfeature.domain.repository.RocketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RocketRepositoryImpl @Inject constructor(
    private val rocketApi: RocketApi,
    private val rocketDao: RocketDao
) : RocketRepository {

    override fun getRockets(): Flow<List<Rocket>> {
        return rocketDao
            .getRockets()
            .map { rocketsCached ->
                rocketsCached.map { it.toDomainModel() }
            }
            .onEach { rockets ->
                if (rockets.isEmpty()) {
                    refreshRockets()
                }
            }
    }

    override suspend fun refreshRockets() {
        rocketApi
            .getRockets()
            .map {
                it.toDomainModel().toEntityModel()
            }
            .also {
                rocketDao.saveRockets(it)
            }
    }
}
