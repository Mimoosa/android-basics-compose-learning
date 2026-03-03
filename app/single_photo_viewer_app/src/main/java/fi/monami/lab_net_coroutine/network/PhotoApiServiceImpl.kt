package fi.monami.lab_net_coroutine.network

import java.io.InputStream
import java.net.URL


public class PhotoApiServiceImpl(val url: String) : PhotoApiService {
    override suspend fun getPhoto(): InputStream {
        return URL(url).openStream()
    }
}
