package uk.co.stellar.domain

data class Asteroid(
    val id: String,
    val neoReferenceId: String,
    val name: String,
    val diameterInMeters: Double,
    val isPotentiallyHazardousAsteroid: Boolean,
    val relativeVelocity: Double?,
    val missDistanceInKm: Double?
)
