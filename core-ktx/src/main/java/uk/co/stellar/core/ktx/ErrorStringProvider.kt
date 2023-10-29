package uk.co.stellar.core.ktx

interface ErrorStringProvider {

    /**
     * Providers a generic error for unexpected issues.
     * @return Unexpected error message
     */
    fun getGenericError(): String
}