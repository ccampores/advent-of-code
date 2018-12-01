import java.io.File
import kotlin.test.assertEquals

fun main() {
    val inputPath = "res/1-2018-input.txt"

    assertEquals(516, sumFrequencies(inputPath))
    assertEquals(71892, firstFreqTwice(inputPath))
}

fun firstFreqTwice(inputPath: String): Int {
    val lines = File(inputPath).readLines()

    if (lines.isEmpty())
        throw Exception("input is empty")

    var sum = 0
    val freqsFound = mutableSetOf(sum)

    while (true) {
        lines.forEach {
            try {
                val el = it.toInt()
                sum += el
            } catch (e: NumberFormatException) {
                println("Invalid input: $it is not a number")
                throw e
            }

            if(!freqsFound.contains(sum))
                freqsFound.add(sum)
            else return sum
        }
    }
}

fun sumFrequencies(inputPath: String) : Int{
    return File(inputPath).useLines {
        it.fold(0) { sum, el -> try {
            sum + el.toInt()
            } catch (e: NumberFormatException) {
                println("Invalid input: $el is not a number")
                throw e
            }
        }
    }
}



