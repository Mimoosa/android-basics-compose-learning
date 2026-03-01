package fi.monami.number_guessing_game_ver2.ui

import androidx.annotation.StringRes
import fi.monami.number_guessing_game_ver2.R

data class NumberGuessGameUiState(
    val guessedNumber: String = "",
    @StringRes val resultText: Int =  R.string.initial_feedback,
    val isResultCorrect: Boolean = false,
    val totalGuessedNum: String = ""
)