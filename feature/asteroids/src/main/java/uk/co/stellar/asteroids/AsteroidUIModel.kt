package uk.co.stellar.asteroids

data class AsteroidUIModel(
    val id: String,
    val name: String,
    val size: String,
    val distance: String?,
    val displaySizeInDps: Int,
    val displayDistanceInDps: Int,
    val isDangerous: Boolean,
)