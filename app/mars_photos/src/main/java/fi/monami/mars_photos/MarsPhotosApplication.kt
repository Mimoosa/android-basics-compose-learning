package fi.monami.mars_photos

import android.app.Application
import fi.monami.mars_photos.data.AppContainer
import fi.monami.mars_photos.data.DefaultAppContainer

class MarsPhotosApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}