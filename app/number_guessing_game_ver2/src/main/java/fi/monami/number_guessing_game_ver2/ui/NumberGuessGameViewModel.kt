package fi.monami.number_guessing_game_ver2.ui.theme

import UpDownGame
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fi.monami.number_guessing_game_ver2.R
import fi.monami.number_guessing_game_ver2.ui.NumberGuessGameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NumberGuessGameViewModel: ViewModel() {
    val game = UpDownGame()

    private var _uiState = MutableStateFlow(NumberGuessGameUiState())
    val uiState = _uiState.asStateFlow()

    fun onValueChange(value: String){
        _uiState.update {
            it.copy(
                guessedNumber = value
            )
        }
    }

    fun onClick(){
        val comparedResult = game.compareNumbers(_uiState.value.guessedNumber.toInt())
        when(comparedResult){
            -1 -> _uiState.update {
                it.copy(
                    resultText = R.string.too_low
                )
            }
            0 -> _uiState.update {
                it.copy(
                    isResultCorrect = true,
                    resultText = R.string.correct_num,
                    totalGuessedNum = game.guessedNumCounter.toString()
                )
            }
            1 -> _uiState.update {
                it.copy(
                    resultText = R.string.too_high
                )
            }
        }
    }
}
