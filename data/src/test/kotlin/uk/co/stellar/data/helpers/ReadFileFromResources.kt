package uk.co.stellar.data.helpers

import java.io.IOException

@Throws(IOException::class)
fun ClassLoader.readFileFromResource(resourceName: String): String {
    return this.getResourceAsStream(resourceName)
        ?.bufferedReader()
        ?.use { reader -> reader.readText() }
        ?: error("Unable to get resource $resourceName")
}
