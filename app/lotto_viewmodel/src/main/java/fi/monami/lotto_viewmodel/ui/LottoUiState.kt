package fi.monami.lotto_viewmodel.ui

data class LottoUiState(
    val selectedNumbers: List<Int> = listOf(),
    val numbersToBeSelected: List<Int> = (1 .. 40).toList(),
    val correctNumbers: List<Int> = listOf(),
    val guessResult: Int? = null
)