package fi.monami.lotto_viewmodel

class Lotto {
    private val correctNumbers = (1 .. 40).toList().shuffled().take(7)
    fun checkHowManyNumbersMatched(guessedNumbers: List<Int>): Int{
        return correctNumbers.intersect(guessedNumbers.toSet()).size
    }
}