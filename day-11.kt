import java.io.File

sealed class Operation {
    class Multiply(val to: Int) : Operation()
    object MultiplyOld : Operation()
    class Add(val to: Int) : Operation()
    object AddOld : Operation()
}

data class Monkey(
    var inspectionNumber: Long = 0,
    val name: String,
    val items: MutableList<Long>,
    val operation: Operation,
    val testDivBy: Long,
    val moveTo: Pair<String, String>
)

fun main() {
    val monkeys = parseInput()
    doMonkeyBusiness(monkeys)
    println(monkeys.sortedByDescending { it.inspectionNumber }.take(2).map { it.inspectionNumber }
        .reduce { a, b -> a * b })
}

private fun parseInput() = File("advert_input.txt").readLines().chunked(7).map {
    val operation = it[2].trim().substring(17).trim()
    Monkey(
        name = it[0][7].toString(),
        items = it[1].trim().substring(16).split(", ").map { it.toLong() }.toMutableList(),
        operation = if (operation.contains("*")) {
            if (operation.split(" * ").all { it == "old" }) Operation.MultiplyOld
            else Operation.Multiply(operation.split(" * ")[1].toInt())
        } else {
            if (operation.split(" + ").all { it == "old" }) Operation.AddOld
            else Operation.Add(operation.split(" + ")[1].toInt())
        },
        testDivBy = it[3].trim().substring(19).toLong(),
        moveTo = it[4].trim().substring(25) to it[5].trim().substring(26)
    )
}

private fun doMonkeyBusiness(monkeys: List<Monkey>) {
    val commonDivisor = monkeys.map { it.testDivBy }.reduce { a, b -> a * b }
    repeat(10000) {
        monkeys.forEach { monkey ->
            monkey.items.forEach { item ->
                val newLevel = when (val oper = monkey.operation) {
                    is Operation.Add -> item + oper.to
                    Operation.AddOld -> item + item
                    is Operation.Multiply -> item * oper.to
                    Operation.MultiplyOld -> item * item
                } % commonDivisor
                monkeys.find {
                    it.name == if (newLevel % monkey.testDivBy == 0L)
                        monkey.moveTo.first
                    else
                        monkey.moveTo.second
                }!!.items.add(newLevel)
                monkey.inspectionNumber++
            }
            monkey.items.clear()
        }
    }
}
