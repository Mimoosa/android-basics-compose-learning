package fi.monami.lab_net_coroutine.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import fi.monami.lab_net_coroutine.network.PhotoApiService

interface PhotoRepository {

    suspend fun getPhoto(): Bitmap
}

class NetworkPhotoRepository(
    private val photoService: PhotoApiService
): PhotoRepository{
    override suspend fun getPhoto(): Bitmap {
        // convert InputStream to Bitmap for the UI
        return BitmapFactory.decodeStream(photoService.getPhoto())
    }
}