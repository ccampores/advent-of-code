package `2018`

import java.io.File
import kotlin.test.assertEquals

class Node(private val children: List<Node>, private val metadata: List<Int>) {

    val allMetadata: Int =
        this.metadata.sum() + this.children.map { it.allMetadata }.sum()

    val value: Int =
        when {
            this.children.isEmpty() -> this.metadata.sum()
            else -> this.metadata.map { this.children.getOrNull(it-1)?.value ?: 0 }.sum()
        }

}

fun main() {
    val exTree = createTree(listOf(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2).iterator())
    assertEquals(138, exTree.allMetadata)
    assertEquals(66, exTree.value)

    val inputPath = "resources/2018/day8.txt"
    val tree = createTree(parse(inputPath).iterator())

    assertEquals(37262, tree.allMetadata)
    assertEquals(20839, tree.value)
}

private fun parse(inputPath: String) : List<Int> {
    return File(inputPath)
        .readText().trim()
        .splitToSequence(" ")
        .map { it.toInt() }
        .toList()
}

fun createTree(i: Iterator<Int>) : Node {
    if(!i.hasNext()) throw Exception()

    val numChildren = i.next()
    val numMetadata = i.next()

    if(numMetadata < 1) throw Exception()

    val children = (0 until numChildren).map { createTree(i) }
    val metadata = (0 until numMetadata).map { i.next() }

    return Node(children, metadata)
}






