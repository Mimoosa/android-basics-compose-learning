package fi.monami.president_app.network

import kotlinx.serialization.Serializable

@Serializable
data class WikiResponse(
    val query: Query
)

@Serializable
data class Query(
    val searchinfo: SearchInfo
)
@Serializable
data class SearchInfo(
    val totalhits: Int
)