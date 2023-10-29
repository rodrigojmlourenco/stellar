package uk.co.stellar.domain

interface AsteroidNeoWsRepository {

    @Throws(AsteroidNeoWsException::class)
    suspend fun getAsteroidsToday(): List<Asteroid>
}
