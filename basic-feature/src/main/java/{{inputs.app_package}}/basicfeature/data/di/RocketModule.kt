package {{inputs.app_package}}.basicfeature.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import {{inputs.app_package}}.basicfeature.data.remote.api.RocketApi
import {{inputs.app_package}}.basicfeature.data.repository.RocketRepositoryImpl
import {{inputs.app_package}}.basicfeature.domain.repository.RocketRepository
import {{inputs.app_package}}.basicfeature.domain.usecase.GetRocketsUseCase
import {{inputs.app_package}}.basicfeature.domain.usecase.RefreshRocketsUseCase
import {{inputs.app_package}}.basicfeature.domain.usecase.getRockets
import {{inputs.app_package}}.basicfeature.domain.usecase.refreshRockets
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RocketModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object RocketModule {

    @Provides
    @Singleton
    fun provideRocketApi(
        retrofit: Retrofit
    ): RocketApi {
        return retrofit.create(RocketApi::class.java)
    }

    @Provides
    fun provideGetRocketsUseCase(
        rocketRepository: RocketRepository
    ): GetRocketsUseCase {
        return {
            getRockets(rocketRepository)
        }
    }

    @Provides
    fun provideRefreshRocketsUseCase(
        rocketRepository: RocketRepository
    ): RefreshRocketsUseCase {
        return {
            refreshRockets(rocketRepository)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        @Singleton
        fun bindRocketRepository(impl: RocketRepositoryImpl): RocketRepository
    }
}
