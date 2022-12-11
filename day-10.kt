import java.io.File

fun main() {
    val steps = File("advert_input.txt").readLines()

    val accValues = mutableListOf<Int>().apply { add(1); add(1) }
    var cycles = 0
    for (i in steps.indices) {
        val step = steps[i]
        cycles += if (step.startsWith("addx")) {
            accValues.add(accValues.last())
            accValues.add(accValues.last() + step.split(" ")[1].toInt())
            2
        } else {
            accValues.add(accValues.last())
            1
        }
    }

    accValues.forEachIndexed { ind, value ->
        println("$ind $value")
    }

    println(
        accValues
            .filterIndexed { index, _ -> index == 20 || ((index - 20) % 40 == 0) }
            .reduceIndexed { index, acc, value ->
                if (index == 1) acc * 20 + value * 60 else acc + value * (index * 40 + 20)
            }
    )
}
