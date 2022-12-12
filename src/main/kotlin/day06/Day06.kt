package day06

val allDistinct = { cs: CharSequence -> cs.length == cs.toSet().size }

class Day06 {

    fun part1(parsed: String): Int =
        parsed.windowedSequence(
            size = 4,
            step = 1,
            partialWindows = false
        ).indexOfFirst(allDistinct) + 4

    fun part2(parsed: String): Int =
        parsed.windowedSequence(
            size = 14,
            step = 1,
            partialWindows = false
        ).indexOfFirst(allDistinct) + 14

}
