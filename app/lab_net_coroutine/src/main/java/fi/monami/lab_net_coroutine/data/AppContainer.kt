package fi.monami.lab_net_coroutine.data

import fi.monami.lab_net_coroutine.network.PhotoApiService
import fi.monami.lab_net_coroutine.network.PhotoApiServiceImpl

interface AppContainer {

    val PhotoRepository: PhotoRepository
}

class DefaultAppContainer: AppContainer{
    private val URL =
    "https://users.metropolia.fi/~jarkkov/folderimage.jpg"

    override val PhotoRepository: PhotoRepository by lazy{
        NetworkPhotoRepository(PhotoApiServiceImpl(URL))
    }

}