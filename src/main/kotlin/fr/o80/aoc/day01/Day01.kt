package fr.o80.aoc.day01

class Day01 {

    fun parse(input: String): List<List<Int>> =
        input.split("\n\n")
            .map { it.lines().map { line -> line.toInt() } }

    fun part1(parsed: List<List<Int>>): Int =
        parsed.maxOf { it.sum() }

    fun part2(parsed: List<List<Int>>): Int =
        parsed.map { it.sum() }
            .sorted()
            .reversed()
            .take(3)
            .sum()


}
