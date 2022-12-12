package day05

import day05.Day05.Step

private typealias Stack = MutableList<Char>
private typealias Storage = MutableList<Stack>

private const val EMPTY = ' '

private fun List<String>.indexOfStackNumbers() = indexOfFirst { it.startsWith(" 1") }

private val stepRegex = """move (\d+) from (\d+) to (\d+)""".toRegex()
private fun String.toStep() = stepRegex.find(this)!!.groupValues.let { (_, amount, from, to) ->
    Step(
        amount = amount.toInt(),
        from = from.toInt(),
        to = to.toInt()
    )
}

// CrateMover 9000 moves one by one
private fun Storage.move(step: Step) {
    (0 until step.amount).forEach { _ ->
        this[step.toIndex].add(this[step.fromIndex].last())
        this[step.fromIndex].removeLast()
    }
}

// CrateMover 9001 moves by batch
private fun Storage.moveV2(step: Step) {
    this[step.toIndex].addAll(this[step.fromIndex].takeLast(step.amount))
    this[step.fromIndex] = this[step.fromIndex].dropLast(step.amount).toMutableList()
}

class Day05 {

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

    fun parse(input: String): Pair<Storage, List<Step>> {
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
            }.toMutableList()
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

    fun part2(parsed: Pair<Storage, List<Step>>): String {
        val (storage, steps) = parsed
        steps.forEach { step -> storage.moveV2(step) }
        return storage.joinToString(separator = "") { stack -> "${stack.last()}" }
    }

}
