package fi.monami.dessert_clicker.ui

import fi.monami.dessert_clicker.R

data class DessertClickerUiState(
    val revenue: Int = 0,
    val dessertSold: Int = 0,
    val dessertPrice: Int = 0,
    val dessertImageId: Int = R.drawable.cupcake
)