package days

class Day23 : Day(23) {
    data class Node(
        val value: Int,
        var next: Node? = null
    ) {
        override fun toString() = "$value"
    }

    override fun partOne(): Any {
        val nodes = inputString
            .map {
                Node(it - '0')
            }
        nodes.zipWithNext { a, b -> a.next = b }
        nodes.last().next = nodes.first()

        var current = nodes.first()
        (0 until 100).forEach { _ ->
            val pickedUp = listOf(current.next, current.next?.next, current.next?.next?.next)
                .mapNotNull { it }
            val pickedUpValues = pickedUp.map { it.value }
            current.next = pickedUp.last().next
            var destination = current.value - 1
            if (destination < 1) {
                destination = 9
            }
            while (pickedUpValues.contains(destination)) {
                destination--
                if (destination < 1) {
                    destination = 9
                }
            }
            var destinationNode = pickedUp.last().next
            while (destinationNode?.value != destination) {
                destinationNode = destinationNode?.next
            }
            pickedUp.last().next = destinationNode.next
            destinationNode.next = pickedUp.first()

            current = current.next ?: return@forEach
        }

        while (current.value != 1) {
            current = current.next ?: break
        }
        current = current.next ?: return Unit
        var string = ""
        while (current.value != 1) {
            string += current.value
            current = current.next ?: break
        }
        return string
    }

    override fun partTwo(): Any {
        val map = mutableMapOf<Int, Node>()
        val nodes = inputString
            .map {
                val node = Node(it - '0')
                map[node.value] = node
                node
            }
            .toMutableList()
        for (i in (nodes.count() + 1)..1000000) {
            val node = Node(i)
            map[node.value] = node
            nodes.add(node)
        }
        nodes.zipWithNext { a, b -> a.next = b }
        nodes.last().next = nodes.first()

        var current = nodes.first()
        (0 until 10000000).forEach { _ ->
            val pickedUp = listOf(current.next, current.next?.next, current.next?.next?.next)
                .mapNotNull { it }
            val pickedUpValues = pickedUp.map { it.value }
            current.next = pickedUp.last().next
            var destination = current.value - 1
            if (destination < 1) {
                destination = nodes.last().value
            }
            while (pickedUpValues.contains(destination)) {
                destination--
                if (destination < 1) {
                    destination = nodes.last().value
                }
            }
            val destinationNode = map[destination] ?: return@forEach
            pickedUp.last().next = destinationNode.next
            destinationNode.next = pickedUp.first()

            current = current.next ?: return@forEach
        }

        current = map[1] ?: return Unit
        return (current.next?.value?.toLong() ?: 0L) * (current.next?.next?.value?.toLong() ?: 0L)
    }
}