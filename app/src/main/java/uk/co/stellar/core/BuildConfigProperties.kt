package uk.co.stellar.core

import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import uk.co.stellar.BuildConfig
import uk.co.stellar.core.ktx.IBuildConfigProperties
import javax.inject.Inject

class BuildConfigProperties @Inject constructor() : IBuildConfigProperties {

    override fun getNasaAPIKey(): String = BuildConfig.NASA_KEY
    override fun getNasaUrl(): String = "https://api.nasa.gov/"

    @dagger.Module
    @InstallIn(SingletonComponent::class)
    interface Module {
        @Binds
        fun bind(impl: BuildConfigProperties): IBuildConfigProperties
    }
}
