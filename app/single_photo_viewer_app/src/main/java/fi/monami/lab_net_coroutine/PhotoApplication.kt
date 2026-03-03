package fi.monami.lab_net_coroutine

import android.app.Application
import fi.monami.lab_net_coroutine.data.AppContainer
import fi.monami.lab_net_coroutine.data.DefaultAppContainer

class PhotoApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}