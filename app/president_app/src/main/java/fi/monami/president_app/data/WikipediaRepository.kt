package fi.monami.president_app.data

import fi.monami.president_app.network.WikiResponse
import fi.monami.president_app.network.retrofitService


class WikipediaRepository(){
    suspend fun getTotalHit(name: String): WikiResponse{
        return retrofitService.searchPresident(name)
    }
}