package fi.monami.dessert_clicker.ui.theme

import androidx.lifecycle.ViewModel
import fi.monami.dessert_clicker.Datasource
import fi.monami.dessert_clicker.Dessert
import fi.monami.dessert_clicker.ui.DessertClickerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DessertClickerUiState())
    val uiState = _uiState.asStateFlow()

    private val desserts = Datasource.dessertList

    fun updateDessertInfo(){
        val updatedDesertSold = _uiState.value.dessertSold + 1
        val updatedDessert = determineDessertToShow(updatedDesertSold)
        val updatedRevenue = _uiState.value.revenue + updatedDessert.price
        updateState(updatedDesertSold, updatedRevenue, updatedDessert)
    }

    fun updateState(dessertSold: Int, revenue: Int, dessert: Dessert){
        _uiState.update { currentState->
            currentState.copy(
                revenue = revenue,
                dessertSold = dessertSold,
                dessertPrice = dessert.price,
                dessertImageId = dessert.imageId
            )
        }
    }

    /**
     * Determine which dessert to show.
     */
    fun determineDessertToShow(
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}