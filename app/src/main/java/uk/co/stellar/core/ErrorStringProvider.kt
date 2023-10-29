package uk.co.stellar.core

import android.content.Context
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.co.stellar.R
import uk.co.stellar.core.ktx.ErrorStringProvider
import javax.inject.Inject

class ErrorStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ErrorStringProvider {
    override fun getGenericError(): String {
        return context.getString(R.string.oops_something_went_wrong)
    }

    @dagger.Module
    @InstallIn(SingletonComponent::class)
    interface Module {
        @Binds
        fun bind(impl: ErrorStringProviderImpl): ErrorStringProvider
    }
}