fun main() {
    val steps = File("advert_input.txt").readLines()

    val tails = mutableListOf<Pair<Int, Int>>().apply { repeat(9) { add(0 to 0) } }
    var head = 0 to 0
    val visited = hashSetOf<Pair<Int, Int>>().apply { add(0 to 0) }

    for (step in steps) {
        val stepsCount = step.split(" ")[1].toInt()
        repeat(stepsCount) {
            head = headStep(step[0], head)
            tailSteps(tails, head)
            visited.add(tails.last())
        }
    }
    print(visited.size)
}

private fun tailSteps(tails: MutableList<Pair<Int, Int>>, head: Pair<Int, Int>) {
    var tmpHead = head
    for (ind in tails.indices) {
        val tail = tails[ind]
        if (tmpHead.first != tail.first || tmpHead.second != tail.second) {
            tails[ind] = if (abs(tmpHead.first - tail.first) > 1 && tmpHead.second == tail.second) {
                (tail.first + (if (tmpHead.first > tail.first) 1 else -1)) to tail.second
            } else if (tmpHead.first == tail.first && abs(tmpHead.second - tail.second) > 1) {
                tail.first to (tail.second + (if (tmpHead.second > tail.second) 1 else -1))
            } else if (
                abs(tmpHead.first - tail.first) > 1 && abs(tmpHead.second - tail.second) > 0 ||
                abs(tmpHead.first - tail.first) > 0 && abs(tmpHead.second - tail.second) > 1
            ) {
                (tail.first + (if (tmpHead.first > tail.first) 1 else -1)) to
                        (tail.second + (if (tmpHead.second > tail.second) 1 else -1))
            } else {
                tail
            }
        }
        tmpHead = tails[ind]
    }
}

private fun headStep(step: Char, head: Pair<Int, Int>): Pair<Int, Int> {
    return when (step) {
        'R' -> head.first to head.second + 1
        'U' -> head.first + 1 to head.second
        'L' -> head.first to head.second - 1
        'D' -> head.first - 1 to head.second
        else -> error("")
    }
}
