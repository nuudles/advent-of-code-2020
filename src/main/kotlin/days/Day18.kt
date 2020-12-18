package days

class Day18 : Day(18) {
    enum class Operator {
        SUM,
        PRODUCT
    }

    sealed class Token {
        data class Value(val value: Long) : Token()
        object StartParen : Token()
        object EndParen : Token()
        object Plus : Token()
        object Times : Token()

        companion object {
            fun tokensFromString(string: String): List<Token> =
                string
                    .split(" ")
                    .flatMap { substring ->
                        when {
                            substring == "+" ->
                                listOf(Plus)
                            substring == "*" ->
                                listOf(Times)
                            substring.startsWith("(") -> {
                                val list = MutableList<Token>(substring.count { it == '(' }) {
                                    StartParen
                                }
                                list.add(Value(substring.trim('(').toLong()))
                                list
                            }
                            substring.endsWith(")") -> {
                                val list = MutableList<Token>(substring.count { it == ')' }) {
                                    EndParen
                                }
                                list.add(0, Value(substring.trim(')').toLong()))
                                list
                            }
                            else ->
                                listOf(Value(substring.toLong()))
                        }
                    }

            fun addPrecedence(tokens: List<Token>): List<Token> {
                val toInsert = mutableSetOf<Pair<Int, Token>>()
                tokens
                    .withIndex()
                    .filter { it.value == Plus }
                    .map { it.index }
                    .forEach { index ->
                        // Find where to add the StartParen
                        if (tokens[index - 1] is Value) {
                            toInsert.add(index - 1 to StartParen)
                        } else if (tokens[index - 1] == EndParen) {
                            var nest = 1
                            var searchIndex = index - 1
                            while (nest > 0) {
                                searchIndex--
                                if (tokens[searchIndex] == StartParen) {
                                    nest--
                                } else if (tokens[searchIndex] == EndParen) {
                                    nest++
                                }
                            }
                            toInsert.add(searchIndex to StartParen)
                        }

                        // Find where to add the EndParen
                        if (tokens[index + 1] is Value) {
                            toInsert.add(index + 2 to EndParen)
                        } else if (tokens[index + 1] == StartParen) {
                            var nest = 1
                            var searchIndex = index + 1
                            while (nest > 0) {
                                searchIndex++
                                if (tokens[searchIndex] == EndParen) {
                                    nest--
                                } else if (tokens[searchIndex] == StartParen) {
                                    nest++
                                }
                            }
                            toInsert.add(searchIndex + 1 to EndParen)
                        }
                    }
                val newTokens = tokens.toMutableList()
                toInsert
                    .sortedByDescending { it.first }
                    .forEach { (index, token) ->
                        newTokens.add(index, token)
                    }
                return newTokens
            }
        }
    }

    sealed class Expression {
        data class Value(val value: Long) : Expression()
        data class Operation(
            val left: Expression,
            val right: Expression,
            val operator: Operator
        ) : Expression()

        fun evaluate(): Long =
            when (this) {
                is Value -> value
                is Operation -> when (operator) {
                    Operator.SUM -> left.evaluate() + right.evaluate()
                    Operator.PRODUCT -> left.evaluate() * right.evaluate()
                }
            }

        companion object {
            fun fromTokens(tokens: List<Token>): Expression {
                if (tokens.count() == 1) {
                    return Value((tokens.last() as? Token.Value)?.value ?: 0)
                }

                var index = tokens.count() - 1
                when (val lastToken = tokens[index]) {
                    is Token.Value ->
                        return Operation(
                            left = fromTokens(tokens.dropLast(2)),
                            right = Value(lastToken.value),
                            operator = when (tokens[index - 1]) {
                                Token.Plus -> Operator.SUM
                                else -> Operator.PRODUCT
                            }
                        )
                    is Token.EndParen -> {
                        var nest = 1
                        while (nest > 0) {
                            index--
                            if (tokens[index] == Token.StartParen) {
                                nest--
                            } else if (tokens[index] == Token.EndParen) {
                                nest++
                            }
                        }
                        return if (index == 0) {
                            fromTokens(tokens.subList(index + 1, tokens.count() - 1))
                        } else {
                            Operation(
                                left = fromTokens(tokens.subList(0, index - 1)),
                                right = fromTokens(tokens.subList(index + 1, tokens.count() - 1)),
                                operator = when (tokens[index - 1]) {
                                    Token.Plus -> Operator.SUM
                                    else -> Operator.PRODUCT
                                }
                            )
                        }
                    }
                    else ->
                        throw Exception("Unexpected!")
                }
            }
        }
    }

    override fun partOne(): Any =
        inputList
            .map { Expression.fromTokens(Token.tokensFromString(it)).evaluate() }
            .sum()

    override fun partTwo(): Any =
        inputList
            .map {
                Expression.fromTokens(
                    Token.addPrecedence(Token.tokensFromString(it))
                ).evaluate()
            }
            .sum()
}