package `2018`

import java.io.File
import kotlin.test.assertEquals

private class Node(val children: List<Node>, val metadata: List<Int>) {
    fun sumMetadata(): Int {
        return this.metadata.sum() + this.children.map { it.sumMetadata() }.sum()
    }
}

fun main() {
    val tree = createTree(listOf(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2).iterator())
    assertEquals(138, tree.sumMetadata())

    val inputPath = "resources/2018/day8.txt"
    assertEquals(37262, part1(inputPath))

}

fun part1(inputPath: String) : Int {
    return createTree(parse(inputPath).iterator()).sumMetadata()
}

private fun parse(inputPath: String) : List<Int> {
    return File(inputPath)
        .readText().trim()
        .splitToSequence(" ")
        .map { it.toInt() }
        .toList()
}

private fun createTree(i: Iterator<Int>) : Node {
    if(!i.hasNext()) throw Exception()

    val numChildren = i.next()
    val numMetadata = i.next()

    if(numMetadata < 1) throw Exception()

    val children = (0 until numChildren).map { createTree(i) }
    val metadata = (0 until numMetadata).map { i.next() }

    return Node(children, metadata)
}






