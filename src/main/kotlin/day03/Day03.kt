package day03

private val itemPriorities: Map<Char, Int> = (('a'..'z') + ('A'..'Z')).mapIndexed { i, c -> c to i + 1 }.toMap()

private typealias RuckSack = Pair<Set<Char>, Set<Char>>

class Day03 {

    fun parse(input: String): List<RuckSack> =
        input.lines().map { line ->
            RuckSack(
                first = line.substring(0, line.length / 2).toSet(),
                second = line.substring(line.length / 2).toSet()
            )
        }

    fun part1(parsed: List<RuckSack>): Int =
        parsed.map { (first, second) -> first.intersect(second).first() }
            .sumOf { itemPriorities[it]!! }

    fun part2(parsed: List<RuckSack>): Int =
        parsed.map { it.first + it.second }
            .windowed(size = 3, step = 3) { (first, second, third) ->
                first.intersect(second).intersect(third).first()
            }
            .sumOf { itemPriorities[it]!! }

}
