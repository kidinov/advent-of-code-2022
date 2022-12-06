fun main() {
    val signal = File("advert_input.txt").readText()
    var left = 0
    val set = LinkedHashSet<Char>()
    while (left < signal.length && set.size < 14) {
        if (set.contains(signal[left])) {
            repeat(set.indexOf(signal[left]) + 1) { set.remove(set.first()) }
        }
        set.add(signal[left])
        left++
    }
    println(left)
}
