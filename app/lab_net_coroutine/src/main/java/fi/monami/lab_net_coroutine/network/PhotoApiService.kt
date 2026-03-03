package fi.monami.lab_net_coroutine.network

import java.io.InputStream


interface PhotoApiService{
    suspend fun getPhoto(): InputStream
}
