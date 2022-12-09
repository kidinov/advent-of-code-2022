fun main() {
    val trees = File("advert_input.txt")
        .readLines()
        .map { it.map { it.toString().toInt() } }

    var max = 0
    for (i in 1 until trees.size - 1) {
        for (j in 1 until trees[0].size - 1) {
            max = max(max,
                trees.calcVisibility((i to j)) { (i1, j1) -> i1 - 1 to j1 } *
                        trees.calcVisibility((i to j)) { (i1, j1) -> i1 + 1 to j1 } *
                        trees.calcVisibility((i to j)) { (i1, j1) -> i1 to j1 - 1 } *
                        trees.calcVisibility((i to j)) { (i1, j1) -> i1 to j1 + 1 }
            )
        }
    }

    println(max)
}

private fun List<List<Int>>.calcVisibility(
    from: Pair<Int, Int>,
    nextFun: (Pair<Int, Int>) -> Pair<Int, Int>
): Int {
    var result = 0

    var next = nextFun(from)
    while (next.first >= 0 && next.second >= 0 && next.first < this.size && next.second < this[0].size) {
        result++
        if (this[from.first][from.second] <= this[next.first][next.second]) {
            break
        }
        next = nextFun(next)

    }
    return result
}
