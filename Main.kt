import kotlin.math.abs
import kotlin.random.Random

fun main() {
    val lf = LifeGame(64, 64, 300)
    lf.run()
}

class LifeGame(private val x: Int, private val y: Int, private val lifeTime: Int) {
    private var grid = Array(x) { arrayOfNulls<Int>(y) }
    private var rand = Random(System.currentTimeMillis())
    private val lis = arrayOf(
        Pair(-1, -1),
        Pair(-1, 0),
        Pair(-1, 1),
        Pair(0, -1),

        Pair(0, 1),
        Pair(1, -1),
        Pair(1, 0),
        Pair(1, 1)
    )

    init {
        for (i in 0 until x) {
            for (j in 0 until y) {
                grid[i][j] = abs(rand.nextInt() % 2)
            }
        }
    }

    fun run() {
        while (true) {
            draw()
            nextGen()
            Thread.sleep(lifeTime.toLong())
        }
    }

    private fun draw() {
        Runtime.getRuntime().exec("clear")
        var tmp: Char
        for (i in 0 until x) {
            for (j in 0 until y) {
                tmp = if (grid[i][j] == 1) {
                    '■'
                } else {
                    '□'
                }
                print(tmp)
            }
            println()
        }
    }

    private fun nextGen() {
        var count: Int
        var x2: Int
        var y2: Int
        var tmp = Array(x) { arrayOfNulls<Int>(y) }
        for (i in 0 until x) {
            for (j in 0 until y) {
                count = 0
                for (k in lis) {
                    x2 = i + k.first
                    y2 = j + k.second
                    if (x2 in 0 until x && y2 in 0 until y) {
                        if (grid[x2][y2] == 1) count++
                    }
                }
                tmp[i][j] = if (grid[i][j] == 0) {
                    if (count == 3) {
                        1
                    } else {
                        0
                    }
                } else {
                    if (count in 2..3) {
                        1
                    } else {
                        0
                    }
                }
            }
        }
        grid = tmp
    }
}
