package fi.monami.mars_photos.fake

import fi.monami.mars_photos.network.MarsApiService
import fi.monami.mars_photos.network.MarsPhoto

class FakeMarsApiService: MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}