package uk.co.stellar.dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
object LocaleModule {

    @Provides
    fun provideLocale(): Locale {
        return Locale.ROOT
    }
}