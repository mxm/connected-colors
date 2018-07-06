import Color.*

enum class Color {
    R,
    B,
    Y,
}

/**
 * Given a color grid, return a map of all colors inside the grid with
 * their maximum number of connected occurrences.
 *
 * A color of a cell is connected if another neighboring cell contains
 * the same color.
 *
 * Runtime is O(n*m) where n and m are the height/width of the grid.
 */
fun findColorConnections(grid : Array<Array<Color>>) : Map<Color, Int> {

    val visited = Array(grid.size, { idx -> Array(grid[idx].size, {false})})

    val resultMap = HashMap<Color, Int>()

    for (y in 0 until grid.size) {
        for (x in 0 until grid[y].size) {
            val color = grid[y][x]
            val numConnections = followColor(color, x, y, grid, visited)
            resultMap.compute(color, {
                _, maybeNum ->
                val current = maybeNum ?: 0
                if (numConnections > current) {
                    numConnections
                } else {
                    current
                }
            })
        }
    }

    return resultMap
}

fun followColor(color: Color, x : Int, y : Int, grid: Array<Array<Color>>, visited : Array<Array<Boolean>>) : Int {
    if (!isValidIndex(x, y, grid) || visited[y][x] || grid[y][x] != color) {
        return 0
    }

    visited.get(y)[x] = true
    return 1 +
            followColor(color,x - 1, y - 1, grid, visited) +
            followColor(color, x, y - 1, grid, visited) +
            followColor(color,x + 1, y - 1, grid, visited) +

            followColor(color,x - 1, y, grid, visited) +
            followColor(color,x + 1, y, grid, visited) +

            followColor(color,x - 1, y + 1, grid, visited) +
            followColor(color, x, y + 1, grid, visited) +
            followColor(color,x + 1, y + 1, grid, visited)
}

fun isValidIndex(x : Int, y : Int, grid : Array<Array<Color>>) : Boolean {
    return y >= 0 && y < grid.size && x >= 0 && x < grid[y].size;
}

/**
 * Some small examples, more tests under ColorConnectionTest.
 */
fun main(args: Array<String>) {

    val grid = arrayOf(
            arrayOf(R, B, Y, B, R),
            arrayOf(B, Y, Y, R, Y),
            arrayOf(R, B, Y, B, B),
            arrayOf(R, B, Y, R, Y))

    println(findColorConnections(grid))

    val grid2 = arrayOf(
            arrayOf(R, B, Y, R, R, R),
            arrayOf(B, Y, Y, R, Y, B),
            arrayOf(R, B, R, B, B, Y),
            arrayOf(R, R, Y, R, Y, Y))

    println(findColorConnections(grid2))

    val grid3 = arrayOf(
            arrayOf(R, B, Y, B),
            arrayOf(B, Y, Y, R),
            arrayOf(R, B, R, B),
            arrayOf(R, B, Y, R))

    println(findColorConnections(grid3))
}