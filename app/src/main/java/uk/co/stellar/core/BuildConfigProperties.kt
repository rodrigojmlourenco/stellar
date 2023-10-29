package uk.co.stellar.core

import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import uk.co.stellar.core.ktx.IBuildConfigProperties
import javax.inject.Inject

class BuildConfigProperties @Inject constructor() : IBuildConfigProperties {

    override fun getNasaAPIKey(): String = "TODO"
    override fun getNasaUrl(): String = "https://api.nasa.gov/"

    @dagger.Module
    @InstallIn(ActivityComponent::class)
    interface Module {
        @Binds
        fun bind(impl: BuildConfigProperties): IBuildConfigProperties
    }
}
