package day04

private typealias Assignments = Pair<IntRange, IntRange>

private fun String.toIntRange(): IntRange = split("-").map { it.toInt() }.let { it[0]..it[1] }

// anonymous function
private val hasCompleteOverlap = fun(assignments: Assignments): Boolean {
    with(assignments) {
        return first.subtract(second).isEmpty() || second.subtract(first).isEmpty()
    }
}

// lambda
private val hasOverlap = { assignments: Assignments ->
    assignments.first.intersect(assignments.second).isNotEmpty()
}

class Day04 {

    fun parse(input: String): List<Assignments> =
        input
            .lines()
            .map { line -> line.split(",") }
            .map { (first, second) ->
                Assignments(
                    first = first.toIntRange(),
                    second = second.toIntRange()
                )
            }

    fun part1(parsed: List<Assignments>): Int = parsed.count(hasCompleteOverlap)

    fun part2(parsed: List<Assignments>): Int = parsed.count(hasOverlap)

}

