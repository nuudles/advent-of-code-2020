package days

class Day22 : Day(22) {
    override fun partOne(): Any {
        val (player1, player2) = inputString.split("\n\n").let { decks ->
            decks.first().split("\n").drop(1).map { it.toLong() }.toMutableList() to
                    decks.last().split("\n").drop(1).map { it.toLong() }.toMutableList()
        }
        while (player1.count() > 0 && player2.count() > 0) {
            if (player1.first() > player2.first()) {
                player1.addAll(listOf(player1.removeFirst(), player2.removeFirst()))
            } else {
                player2.addAll(listOf(player2.removeFirst(), player1.removeFirst()))
            }
        }
        val winner = if (player1.count() > 0) player1 else player2
        return winner
            .withIndex()
            .fold(0L) { sum, entry -> sum + entry.value * (winner.count() - entry.index) }
    }

    private fun playGame(
        decks: Pair<MutableList<Int>, MutableList<Int>>
    ): Pair<List<Int>, List<Int>> {
        val configurations = mutableSetOf<Pair<List<Int>, List<Int>>>()
        while (decks.first.count() > 0 && decks.second.count() > 0) {
            if (configurations.contains(decks)) {
                // Player 1 wins
                decks.first.addAll(decks.second)
                decks.second.clear()
                break
            }
            configurations.add(decks)

            val cards = decks.first.removeFirst() to decks.second.removeFirst()
            if (decks.first.count() >= cards.first && decks.second.count() >= cards.second) {
                playGame(
                    decks.first.subList(0, cards.first).toMutableList() to
                            decks.second.subList(0, cards.second).toMutableList()
                ).apply {
                    if (first.count() > second.count()) {
                        decks.first.addAll(listOf(cards.first, cards.second))
                    } else {
                        decks.second.addAll(listOf(cards.second, cards.first))
                    }
                }
            } else {
                if (cards.first > cards.second) {
                    decks.first.addAll(listOf(cards.first, cards.second))
                } else {
                    decks.second.addAll(listOf(cards.second, cards.first))
                }
            }
        }
        return decks
    }

    override fun partTwo(): Any {
        val decks = inputString
            .split("\n\n").let { decks ->
                decks.first().split("\n").drop(1).map { it.toInt() }.toMutableList() to
                        decks.last().split("\n").drop(1).map { it.toInt() }.toMutableList()
            }
        val winner = playGame(decks).run { if (first.count() > 0) first else second }
        return winner
            .withIndex()
            .fold(0) { sum, entry -> sum + entry.value * (winner.count() - entry.index) }
    }
}