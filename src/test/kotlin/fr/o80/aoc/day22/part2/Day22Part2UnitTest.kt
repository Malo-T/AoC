package fr.o80.aoc.day22.part2

import fr.o80.aoc.day22.Day22
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class Day22Part2UnitTest {

    private val day = Day22()

    @ParameterizedTest
    @MethodSource("provide")
    fun computePart2(input: String, expectedOutput: Int) {
        // when
        val result = day.part2(day.parse2(input))

        // then
        assertEquals(expectedOutput, result)
    }

    companion object {
        @JvmStatic
        fun provide(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(input_d22_p2_1, result_d22_p2_1),
                Arguments.of(input_d22_p2_2, result_d22_p2_2),
                Arguments.of(input_d22_p2_3, result_d22_p2_3),
                Arguments.of(exercise_d22_p2, -1),
            )
        }

    }

}