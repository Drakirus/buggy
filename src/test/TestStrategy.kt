import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by drakirus (p.champion) on 16/03/18.
 */

class TestStrategy {


    @Test
    fun testDefault() {
        val diminuende = getRandomNumber(10, 100)
                .toNumList() // top

        val diminuteur = getRandomNumber(0, diminuende.init_value)
                .toNumList() // bot

        diminuende.minusStrategy = defaultMinusStrategy
        assertEquals((diminuende - diminuteur).init_value, diminuende.init_value - diminuteur.init_value, "default subtracts")
    }

    @Test
    fun testSmallerMinusMaxStrategy() {
        val diminuende = 143
                .toNumList() // top

        val diminuteur = 28
                .toNumList() // bot

        diminuende.minusStrategy = smallerMinusMaxStrategy
        assertEquals((diminuende - diminuteur).init_value, 125, "Smaller Minus")
    }

    @Test
    fun testBorrowNoDeduction() {
        val diminuende = 143
                .toNumList() // top

        val diminuteur = 28
                .toNumList() // bot

        diminuende.minusStrategy = borrowNoDeduction
        assertEquals((diminuende - diminuteur).init_value, 125, "Borrow No Deduction")
    }

    @Test
    fun testBorrowFrom0() {
        val diminuende = 1_300
                .toNumList() // top

        val diminuteur = 522
                .toNumList() // bot

        diminuende.minusStrategy = borrowFrom0
        assertEquals((diminuende - diminuteur).init_value, 878, "Borrow From 0")
    }

    @Test
    fun testTopDigitEqual0() {
        val diminuende = 140
                .toNumList() // top

        val diminuteur = 21
                .toNumList() // bot

        diminuende.minusStrategy = topDigitEqual0
        assertEquals((diminuende - diminuteur).init_value, 121, "Top Digit Equal 0")
    }
}

