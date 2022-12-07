package fr.o80.aoc.day02

class Day02 {

    enum class Play(val points: Int) {
        ROCK(1) {
            override fun scoreAgainst(other: Play) = when (other) {
                ROCK -> Result.DRAW
                PAPER -> Result.LOSS
                SCISSORS -> Result.WIN
            }.points + points
        },
        PAPER(2) {
            override fun scoreAgainst(other: Play) = when (other) {
                ROCK -> Result.WIN
                PAPER -> Result.DRAW
                SCISSORS -> Result.LOSS
            }.points + points
        },
        SCISSORS(3) {
            override fun scoreAgainst(other: Play) = when (other) {
                ROCK -> Result.LOSS
                PAPER -> Result.WIN
                SCISSORS -> Result.DRAW
            }.points + points
        };

        abstract fun scoreAgainst(other: Play): Int
    }

    enum class Result(val points: Int) {
        LOSS(0), DRAW(3), WIN(6)
    }

    enum class OpponentPlay(val play: Play) {
        A(Play.ROCK), B(Play.PAPER), C(Play.SCISSORS)
    }

    enum class ResponsePlay(val play: Play) {
        X(Play.ROCK), Y(Play.PAPER), Z(Play.SCISSORS)
    }

    enum class ExpectedResult(val result: Result) {
        X(Result.LOSS) {
            override fun expectedResponse(opponentPlay: Play) = when (opponentPlay) {
                Play.ROCK -> Play.SCISSORS
                Play.PAPER -> Play.ROCK
                Play.SCISSORS -> Play.PAPER
            }
        },
        Y(Result.DRAW) {
            override fun expectedResponse(opponentPlay: Play) = opponentPlay
        },
        Z(Result.WIN) {
            override fun expectedResponse(opponentPlay: Play) = when (opponentPlay) {
                Play.ROCK -> Play.PAPER
                Play.PAPER -> Play.SCISSORS
                Play.SCISSORS -> Play.ROCK
            }
        };

        abstract fun expectedResponse(opponentPlay: Play): Play
    }

    fun parse1(input: String): List<Pair<OpponentPlay, ResponsePlay>> =
        input.split("\n")
            .map {
                it.split(" ")
                    .let { pair ->
                        OpponentPlay.valueOf(pair[0]) to ResponsePlay.valueOf(pair[1])
                    }
            }

    fun part1(parsed: List<Pair<OpponentPlay, ResponsePlay>>): Int =
        parsed.sumOf { (opponent, response) ->
            response.play.scoreAgainst(opponent.play)
        }

    fun parse2(input: String): List<Pair<OpponentPlay, ExpectedResult>> =
        input.split("\n")
            .map {
                it.split(" ")
                    .let { pair ->
                        OpponentPlay.valueOf(pair[0]) to ExpectedResult.valueOf(pair[1])
                    }
            }

    fun part2(parsed: List<Pair<OpponentPlay, ExpectedResult>>): Int =
        parsed.sumOf { (opponent, expectedResult) ->
            expectedResult.expectedResponse(opponent.play).scoreAgainst(opponent.play)
        }

}
