package uk.co.stellar.data

import uk.co.stellar.core.ktx.DateProvider
import uk.co.stellar.domain.Asteroid
import uk.co.stellar.domain.AsteroidNeoWsRepository

class AsteroidNeoWsRepositoryImpl constructor(
    private val dataSource: AsteroidsRemoteDataSource,
    private val dateProvider: DateProvider
) : AsteroidNeoWsRepository {

    override suspend fun getAsteroidsToday(): List<Asteroid> {
        return dataSource.getAsteroidNews(dateProvider.getToday(), dateProvider.getToday()).values.flatten()
    }
}
