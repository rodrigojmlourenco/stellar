package uk.co.stellar.data

import NearEarthObject
import uk.co.stellar.domain.Asteroid

internal fun NearEarthObject.toAsteroid(): Asteroid {
    return Asteroid(
        this.id,
        this.neoReferenceId,
        this.name,
        this.estimatedDiameter.meters.min,
        this.isPotentiallyHazardousAsteroid,
        this.closeApproachData.firstOrNull()?.relativeVelocity?.kilometersPerHour,
        this.closeApproachData.firstOrNull()?.missDistance?.kilometers,
    )
}
