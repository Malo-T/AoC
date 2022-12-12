package day05

typealias Stack = MutableList<Char>
typealias Storage = List<Stack>

const val EMPTY = ' '

data class Step(
    val amount: Int,
    val from: Int,
    val to: Int,
) {
    val fromIndex: Int
        get() = from - 1
    val toIndex: Int
        get() = to - 1
}

fun List<String>.indexOfStackNumbers() = indexOfFirst { it.startsWith(" 1") }

val stepRegex = """move (\d+) from (\d+) to (\d+)""".toRegex()
fun String.toStep() = stepRegex.find(this)!!.groupValues.let { (_, amount, from, to) ->
    Step(
        amount = amount.toInt(),
        from = from.toInt(),
        to = to.toInt()
    )
}

fun Storage.move(step: Step) {
    (0 until step.amount).forEach { _ ->
        this[step.toIndex].add(this[step.fromIndex].last())
        this[step.fromIndex].removeLast()
    }
}

class Day05 {

    fun parse1(input: String): Pair<Storage, List<Step>> {
        val storage: Storage = input
            .lines()
            .let { lines -> lines.subList(0, lines.indexOfStackNumbers()) }
            .map { row -> row.chunked(4).map { it[1] } }
            // transform rows to columns (stacks)
            .let { rows ->
                rows.fold(
                    initial = List(rows.first().size) { mutableListOf<Char>() },
                    operation = { columns, row ->
                        columns.apply {
                            forEachIndexed { columnIndex, column ->
                                // remove empty values
                                if (row[columnIndex] != EMPTY) {
                                    column.add(row[columnIndex])
                                }
                            }
                        }
                    }
                ).map { it.reversed().toMutableList() } // bottom to top of the stack
            }
            .onEach { println(it) }

        val steps = input
            .lines()
            .let { lines -> lines.subList(lines.indexOfStackNumbers() + 2, lines.size) }
            .map { it.toStep() }
            .onEach { println(it) }

        return storage to steps
    }

    fun part1(parsed: Pair<Storage, List<Step>>): String {
        val (storage, steps) = parsed
        steps.forEach { step -> storage.move(step) }
        return storage.joinToString(separator = "") { stack -> "${stack.last()}" }
    }

    fun parse2(input: String): List<Int> {
        TODO()
    }

    fun part2(parsed: List<Int>): Int {
        TODO()
    }

}
