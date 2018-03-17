/**
 * Created by drakirus (p.champion) on 15/03/18.
 */

fun getRandomNumber(min: Int, max: Int): Int {
    return Math.floor(Math.random() * (max - min + 1)).toInt() + min
}

// add method to the default Int class
fun Int.toNumList(): NumList {
    return NumList(this)
}

fun quetion(
        strategy: (NumList, NumList) -> NumList,
        afterQuestion: (result: NumList) -> Unit
) {
    val diminuende = getRandomNumber(10, 100)
            .toNumList() // top

    val diminuteur = getRandomNumber(0, diminuende.toInt())
            .toNumList() // bot

    diminuende.prettyPrintMinus(diminuteur)
    diminuende.minusStrategy = strategy
    afterQuestion(diminuende - diminuteur)
}

fun play(strategy: (NumList, NumList) -> NumList) {
    // learn
    do {
        quetion(strategy) { result ->
            println(result)
            print("\nKeep Learning? [Y/n] ")
        }
    } while (readLine() != "n" )

    // ask the user to find the subtracts strategy
    println("What the answer? ")

    val sequenceLen = 1
    for (i in 0..sequenceLen) {
        quetion(strategy) { result ->
            val response = readLine()?.toString()?.toIntOrNull() ?: 0
            if (result.toInt() == response) {
                print("You got it!! ")
                if (i == sequenceLen) {
                    println(result.strategyName)
                } else {
                    println("Keep going")
                }
            } else {
                println("Correct answer = $result | Failed get back to work!!")
                play(strategy) // go back to learning
            }
        }
    }
}

fun main(args: Array<String>) {
    val strategies = listOf(
//            defaultMinusStrategy,
            smallerMinusMaxStrategy,
            borrowNoDeduction,
            topDigitEqual0,
            borrowFrom0)

    play(strategies.shuffled().first())
}

