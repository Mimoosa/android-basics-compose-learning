package fi.monami.lotto_viewmodel.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import fi.monami.lotto_viewmodel.Lotto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LottoViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LottoUiState())
    val uiState = _uiState.asStateFlow()

    var myLotto = Lotto()

    fun addNumToSelectedList(num: Int){
        if(_uiState.value.selectedNumbers.size <= 8){
            val updatedList = _uiState.value.selectedNumbers + listOf(num).sorted()
            _uiState.update {
                it.copy(
                    selectedNumbers = updatedList
                )
            }

            removeNumFromNumListTOBeSelected(num)
        }
    }

    fun removeNumFromSelectedList(num: Int){
        val updatedList = _uiState.value.selectedNumbers.minus(listOf(num).toSet()).sorted()
        _uiState.update {
            it.copy(
                selectedNumbers = updatedList
            )
        }
        addNumToNumListToBeSelected(num)
    }

    private fun removeNumFromNumListTOBeSelected(num: Int){
        val updatedList = _uiState.value.numbersToBeSelected.minus(listOf(num).toSet()).sorted()
        _uiState.update {
            it.copy(
                numbersToBeSelected = updatedList
            )
        }
    }

    private fun addNumToNumListToBeSelected(num: Int){
        val updatedList = _uiState.value.numbersToBeSelected + listOf(num).sorted()
        _uiState.update {
            it.copy(
                numbersToBeSelected = updatedList
            )
        }
    }

    fun checkHowManyNumbersMatched(){
        val matchedNum =  myLotto.checkHowManyNumbersMatched(_uiState.value.selectedNumbers)
        _uiState.update {
            it.copy(
                guessResult = matchedNum
            )
        }
    }

    fun reset(){
        myLotto = Lotto()
        _uiState.update {
            it.copy(
                selectedNumbers = listOf<Int>(),
                numbersToBeSelected = (1 .. 40).toList(),
                correctNumbers = listOf<Int>(),
                guessResult = null
            )
        }
    }
}