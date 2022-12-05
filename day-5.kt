fun main() {
    val array = File("advert_input.txt").readText().split("\n\n")
    val (stacksInput, movesInput) = (array[0] to array[1])
    val positions = stacksInput.lines().find { it.startsWith(" 1") }!!
    val stacks = buildStacks(stacksInput, positions)
    val moves = buildMoves(movesInput)
    makeMoves(moves, stacks)
    println(stacks.map { it.value.last() }.joinToString(separator = ""))
}

private fun makeMoves(moves: List<Triple<Int, Int, Int>>, stacks: MutableMap<Int, Stack<Char>>) {
    moves.forEach { move ->
        val stackToMoveFrom = stacks[move.second]!!
        val stackToMoveTo = stacks[move.third]!!
        stackToMoveTo.addAll(
            stackToMoveFrom.toList().subList(stackToMoveFrom.size - move.first, stackToMoveFrom.size)
        )
        repeat(move.first) { stackToMoveFrom.pop() }
    }
}

private fun buildMoves(movesInput: String): List<Triple<Int, Int, Int>> {
    return movesInput.lines()
        .map { it.split("\\W+".toRegex()).filter { it.all { it.isDigit() } } }
        .map { Triple(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
}

private fun buildStacks(stacksInput: String, positions: String): MutableMap<Int, Stack<Char>> {
    val stacks = mutableMapOf<Int, Stack<Char>>()
    stacksInput
        .lines()
        .reversed()
        .filterNot { it.startsWith(" 1") }
        .forEach {
            it.forEachIndexed { i, char ->
                if (char.isLetter()) {
                    val stackNumber = positions[i].toString().toInt()
                    stacks.putIfAbsent(stackNumber, Stack())
                    stacks[stackNumber]!!.add(char)
                }
            }
        }
    return stacks
}
