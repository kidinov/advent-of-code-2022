import java.io.File
import java.lang.Integer.min
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val matrix = File("advert_input.txt").readLines().map { it.toCharArray().toMutableList() }

    val startS = matrix.findCode('S').first()
    val end = matrix.findCode('E').first()
    matrix[startS.first][startS.second] = 'a'
    matrix[end.first][end.second] = 'z'

    val starts = matrix.findCode('a')

    var min = Int.MAX_VALUE
    for (start in starts) {
        val steps = matrix.bfs(start, end)
        min = min(min, steps - 1)
    }
    println(min)
}

private fun List<MutableList<Char>>.bfs(start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
    val visited = HashSet<Pair<Int, Int>>()
    val q = LinkedList<Pair<Int, Int>>()
    q.push(start)
    visited.add(start)

    var steps = 0
    loop@ while (q.isNotEmpty()) {
        val qSize = q.size
        steps++
        for (i in 0 until qSize) {
            val cur = q.poll()

            if (cur == end) return steps

            if (cur.second != 0 && this[cur.first][cur.second] + 1 >= this[cur.first][cur.second - 1]) {
                q.addIfNotVisited(visited, cur.first to cur.second - 1)
            }
            if (cur.second < this[0].size - 1 && this[cur.first][cur.second] + 1 >= this[cur.first][cur.second + 1]) {
                q.addIfNotVisited(visited, cur.first to cur.second + 1)
            }
            if (cur.first != 0 && this[cur.first][cur.second] + 1 >= this[cur.first - 1][cur.second]) {
                q.addIfNotVisited(visited, cur.first - 1 to cur.second)
            }
            if (cur.first < this.size - 1 &&
                this[cur.first][cur.second] + 1 >= this[cur.first + 1][cur.second]
            ) {
                q.addIfNotVisited(visited, cur.first + 1 to cur.second)
            }
        }
    }
    error("")
}

private fun LinkedList<Pair<Int, Int>>.addIfNotVisited(visited: HashSet<Pair<Int, Int>>, coord: Pair<Int, Int>) {
    if (!visited.contains(coord)) {
        visited.add(coord)
        add(coord)
    }
}

private fun List<List<Char>>.findCode(char: Char) =
    mapIndexed { index, ints -> index to ints.indexOfFirst { it == char } }.filter { it.second != -1 }
