import Color.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.Test
import org.junit.Assert.assertThat

class ColorConnectionsTest {

    @Test
    fun test1x1() {
        val grid = arrayOf(arrayOf(R))

        assertThat(findColorConnections(grid), `is`(mapOf(Pair(R, 1))))
    }

    @Test
    fun test3x1() {
        val grid = arrayOf(arrayOf(R, B, Y))

        assertThat(findColorConnections(grid), `is`(mapOf(Pair(R, 1), Pair(B, 1), Pair(Y, 1))))
    }

    @Test
    fun test1x3() {
        val grid = arrayOf(arrayOf(R), arrayOf(B), arrayOf(Y))

        assertThat(findColorConnections(grid), `is`(mapOf(Pair(R, 1), Pair(B, 1), Pair(Y, 1))))
    }

    @Test
    fun test4x5() {
        val grid = arrayOf(
                arrayOf(R, B, Y, B, R),
                arrayOf(B, Y, Y, R, Y),
                arrayOf(R, B, Y, B, B),
                arrayOf(R, B, Y, R, Y))

        assertThat(findColorConnections(grid), `is`(mapOf(Pair(R, 2), Pair(B, 4), Pair(Y, 5))))
    }

    @Test
    fun test4x6() {
        val grid = arrayOf(
                arrayOf(R, B, Y, R, R, R),
                arrayOf(B, Y, Y, R, Y, B),
                arrayOf(R, B, R, B, B, Y),
                arrayOf(R, R, Y, R, Y, Y))


        assertThat(findColorConnections(grid), `is`(mapOf(Pair(R, 9), Pair(B, 3), Pair(Y, 4))))
    }

    @Test
    fun test4x4() {
        val grid = arrayOf(
                arrayOf(R, B, Y, B),
                arrayOf(B, Y, Y, R),
                arrayOf(R, B, R, B),
                arrayOf(R, B, Y, R))

        assertThat(findColorConnections(grid), `is`(mapOf(Pair(R, 3), Pair(B, 4), Pair(Y, 3))))
    }

}