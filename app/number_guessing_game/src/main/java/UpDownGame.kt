class UpDownGame {
    val correctNum = (1..100).random()
    var guessedNumCounter = 0
    fun compareNumbers(guessedNum: Int): Int{
        guessedNumCounter ++
        return if(guessedNum < correctNum)
            -1
        else if(guessedNum == correctNum)
            0
        else
            1
    }
}