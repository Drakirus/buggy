/**
 * Created by drakirus (p.champion) on 16/03/18.
 */

class NumList(private val init_value: Int) {

    val digits: List<Int> = init_value.toString().toCharArray().map { Character.getNumericValue(it) }
    var minusStrategy: (NumList, NumList) -> NumList = defaultMinusStrategy
    var strategyName = "hidden"

    override fun toString(): String {
        return digits.fold("") { acc, i ->  acc + i }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        other as NumList
        return init_value == other.init_value
    }

    operator fun minus(diminuteur: NumList): NumList {
        return minusStrategy(this, diminuteur)
    }

    fun toInt(): Int = init_value

    fun prettyPrintMinus(diminuteur: NumList) {
        val lenTop = this.digits.size
        println("  " + this)
        println("- " + diminuteur.toString().padStart(lenTop))
        println("--" + "".toString().padStart(lenTop, padChar = '-'))
    }

    class Builder {
        private val digits = mutableListOf<Int>()
        var strategyName = "No strategyName"

        fun addDigit(digit: Int) { digits += digit }

        fun build(): NumList {
            val res = digits
                    .fold("") { acc, i -> acc + i }
                    .toInt()
                    .toNumList()

            res.strategyName = strategyName
            return res
        }

        fun reverse(): Builder {
            digits.reverse()
            return this
        }
    }
}

