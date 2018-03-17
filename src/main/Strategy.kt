import kotlin.math.max
import kotlin.math.min

/**
 * Created by drakirus (p.champion) on 15/03/18.
 */

// the default and correct way to Subtract
val defaultMinusStrategy: ((top: NumList, bot: NumList) -> NumList) = { topNum, botNum ->
    val top = topNum.digits.reversed()
    val bot = botNum.digits.reversed()

    var deduction = 0
    val result = NumList.Builder()

    for (i in 0..top.size) {
        val topDigit = top.getOrElse(i, { 0 })
        val botDigit = bot.getOrElse(i, { 0 })
        val diff = topDigit - (botDigit + deduction)

        val value = if (diff < 0) {
            deduction = 1
            10 + diff
        } else {
            deduction = 0
            diff + deduction
        }

        result.addDigit(value)
    }
    result.strategyName = "default Subtract"
    result.reverse().build()
}

// the student substracts the smaller digit in each column from the larger digit regardless of which is on top.
val smallerMinusMaxStrategy: ((top: NumList, bot: NumList) -> NumList) = {topNum, botNum ->
    val top = topNum.digits.reversed()
    val bot = botNum.digits.reversed()

    val result = NumList.Builder()

    for (i in 0..top.size) {
        val topDigit = max(top.getOrElse(i, { 0 }), bot.getOrElse(i, { 0 }))
        val botDigit = min(top.getOrElse(i, { 0 }), bot.getOrElse(i, { 0 }))

        result.addDigit(topDigit - botDigit)
    }

    result.strategyName = "The student substracts the smaller digit in each column from the larger digit regardless of which is on top."
    result.reverse().build()

}

// when the student needs to borrow, he adds 10 to the top digit of the current column without subtracting 1 from the next column to the left.
val borrowNoDeduction: ((top: NumList, bot: NumList) -> NumList) = {topNum, botNum ->
    val top = topNum.digits.reversed()
    val bot = botNum.digits.reversed()

    val result = NumList.Builder()

    for (i in 0..top.size) {
        val topDigit = top.getOrElse(i, { 0 })
        val botDigit = bot.getOrElse(i, { 0 })
        val diff = topDigit - botDigit

        val value = if (diff < 0) {
            10 + diff
        } else {
            diff
        }

        result.addDigit(value)
    }
    result.strategyName = "When the student needs to borrow, he adds 10 to the top digit of the current column without subtracting 1 from the next column to the left."
    result.reverse().build()

}

// when borrowing from a column whose top digit is 0, the student writes 9 but does not continue borrowing from the column to the left of the 0.
val borrowFrom0: ((top: NumList, bot: NumList) -> NumList) = {topNum, botNum ->
    val top = topNum.digits.reversed().toMutableList()
    val bot = botNum.digits.reversed()

    val result = NumList.Builder()
    var deduction = 0

    for (i in 0..top.size) {
        val topDigit = top.getOrElse(i, { 0 })
        val botDigit = bot.getOrElse(i, { 0 })
        val diff = topDigit - (botDigit + deduction)

        val value = if (diff < 0) {

            val onLeft = top.getOrElse(i + 1, { 1 })
            if (onLeft == 0) {
                deduction = 0
                top.set(i + 1, 9)
            } else {
                deduction = 1
            }

            10 + diff
        } else {
            deduction = 0
            diff
        }

        result.addDigit(value)
    }
    result.strategyName = "When borrowing from a column whose top digit is 0, the student writes 9 but does not continue borrowing from the column to the left of the 0."
    result.reverse().build()
}

// Whenever the top digit in a column is 0, the student writes the bottom digit in the answer.
val topDigitEqual0: ((top: NumList, bot: NumList) -> NumList) = { topNum, botNum ->
    val top = topNum.digits.reversed()
    val bot = botNum.digits.reversed()

    var deduction = 0
    val result = NumList.Builder()

    for (i in 0..top.size) {
        val topDigit = top.getOrElse(i, { 0 })
        val botDigit = bot.getOrElse(i, { 0 })
        val diff = topDigit - (botDigit + deduction)

        val value = if (diff < 0) {
            deduction = 1
            10 + diff
        } else {
            deduction = 0
            diff + deduction
        }

        when (topDigit) {
            0 -> {
                result.addDigit(botDigit)
                deduction = 0
            }
            else -> result.addDigit(value)
        }

    }
    result.strategyName = "Whenever the top digit in a column is 0, the student writes the bottom digit in the answer."
    result.reverse().build()
}
