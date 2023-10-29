import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("next") val next: String?,
    @SerialName("prev") val prev: String?,
    @SerialName("self") val self: String?
)

@Serializable
data class EstimatedDiameter(
    @SerialName("kilometers") val kilometers: DiameterUnit,
    @SerialName("meters") val meters: DiameterUnit,
    @SerialName("miles") val miles: DiameterUnit,
    @SerialName("feet") val feet: DiameterUnit
)

@Serializable
data class DiameterUnit(
    @SerialName("estimated_diameter_min") val min: Double,
    @SerialName("estimated_diameter_max") val max: Double
)

@Serializable
data class CloseApproachData(
    @SerialName("close_approach_date") val closeApproachDate: String,
    @SerialName("close_approach_date_full") val closeApproachDateFull: String,
    @SerialName("epoch_date_close_approach") val epochDateCloseApproach: Long,
    @SerialName("relative_velocity") val relativeVelocity: RelativeVelocity,
    @SerialName("miss_distance") val missDistance: MissDistance,
    @SerialName("orbiting_body") val orbitingBody: String
)

@Serializable
data class RelativeVelocity(
    @SerialName("kilometers_per_second") val kilometersPerSecond: Double,
    @SerialName("kilometers_per_hour") val kilometersPerHour: Double,
    @SerialName("miles_per_hour") val milesPerHour: Double
)

@Serializable
data class MissDistance(
    @SerialName("astronomical") val astronomical: Double,
    @SerialName("lunar") val lunar: Double,
    @SerialName("kilometers") val kilometers: Double,
    @SerialName("miles") val miles: Double
)

@Serializable
data class NearEarthObject(
    @SerialName("id") val id: String,
    @SerialName("neo_reference_id") val neoReferenceId: String,
    @SerialName("name") val name: String,
    @SerialName("absolute_magnitude_h") val absoluteMagnitudeH: Double,
    @SerialName("estimated_diameter") val estimatedDiameter: EstimatedDiameter,
    @SerialName("is_potentially_hazardous_asteroid") val isPotentiallyHazardousAsteroid: Boolean,
    @SerialName("close_approach_data") val closeApproachData: List<CloseApproachData>,
    @SerialName("is_sentry_object") val isSentryObject: Boolean
)

@Serializable
data class NeoFeedResponse(
    @SerialName("links") val links: Links?,
    @SerialName("element_count") val elementCount: Int,
    @SerialName("near_earth_objects") val nearEarthObjects: Map<String, List<NearEarthObject>>
)
