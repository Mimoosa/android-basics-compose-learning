package fi.monami.president_app.ui.screens

import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import fi.monami.president_app.data.WikipediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PresidentApiViewModel: ViewModel() {
    private val repository: WikipediaRepository = WikipediaRepository()

    var wikiUiState: Int by mutableStateOf(0)
        private set

    fun getHits(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            val serverResp = repository.getTotalHit(name)
            wikiUiState = serverResp.query.searchinfo.totalhits
        }
    }
}