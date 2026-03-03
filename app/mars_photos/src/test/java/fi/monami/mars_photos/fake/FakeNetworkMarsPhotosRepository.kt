package fi.monami.mars_photos.fake

import fi.monami.mars_photos.data.MarsPhotosRepository
import fi.monami.mars_photos.network.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}