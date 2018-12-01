import java.io.File
import kotlin.test.assertEquals

fun main() {
    val inputPath = "res/1-2018-input.txt"

    assertEquals(516, sumFrequencies(inputPath))
    assertEquals(71892, firstFreqTwice(inputPath))
}

fun firstFreqTwice(inputPath: String): Int {
    val lines = File(inputPath).readLines()
    var sum = 0
    val freqsFound = mutableSetOf(sum)
    do {
        lines.forEach {
            val el = it.toInt()
            sum += el
            if(!freqsFound.contains(sum))
                freqsFound.add(sum)
            else return sum
        }
    } while (true)
}

fun sumFrequencies(inputPath: String) : Int{
    //TODO check input
    return File(inputPath).useLines {
        it.fold(0) { sum, el -> sum + el.toInt() }
    }
}



