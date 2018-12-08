package `2018`

import java.io.File
import java.util.*
import kotlin.test.assertEquals

fun main() {
    val inputPath = "resources/2018/day5.txt"
    val input = File(inputPath).readText()

    assertEquals(10, reaction("dabAcCaCBAcCcaDA"))
    assertEquals(11108, reaction(input))

    assertEquals(4, minLengthReaction("dabAcCaCBAcCcaDA"))
    assertEquals(5094, minLengthReaction(input))

}

fun minLengthReaction(input: String) : Int {
    var minLength = Int.MAX_VALUE

    ('a'..'z').forEach { c ->
        val filteredInput = input.filter { (it != c) && (it != c.toUpperCase()) }
        val length = reaction(filteredInput)
        if (length < minLength)
            minLength = length
    }

    return minLength
}

fun reaction(input: String): Int {
    val stackSeen = Stack<Char>()

    input.forEach {
        if (stackSeen.isNotEmpty()) {
            if (!isReaction(stackSeen.peek(), it))
                stackSeen.push(it)
            else stackSeen.pop()
        }
        else stackSeen.push(it)
    }

    return stackSeen.size
}

fun isReaction(c1: Char, c2: Char) : Boolean {

    return when  {
        c1.isLowerCase() -> c2.isUpperCase() && (c1 == c2.toLowerCase())
        c1.isUpperCase() -> c2.isLowerCase() && (c1 == c2.toUpperCase())
        else -> false
    }
}


