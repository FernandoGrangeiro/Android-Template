package {{inputs.app_package}}.basicfeature.presentation.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import {{inputs.app_package}}.basicfeature.presentation.RocketsNavigationFactory
import {{inputs.app_package}}.basicfeature.presentation.RocketsUiState
import {{inputs.app_package}}.core.navigation.NavigationFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RocketsViewModelModule {

    @Provides
    fun provideInitialRocketsUiState(): RocketsUiState = RocketsUiState()
}

@Module
@InstallIn(SingletonComponent::class)
interface RocketsSingletonModule {

    @Singleton
    @Binds
    @IntoSet
    fun bindRocketsNavigationFactory(factory: RocketsNavigationFactory): NavigationFactory
}
