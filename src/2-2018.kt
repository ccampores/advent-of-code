import java.io.File
import kotlin.test.assertEquals

fun main() {
    val inputPath = "res/2-2018-input.txt"
    val exInputPath = "res/2-2018-exInput.txt"

    assertEquals(12, checksum(exInputPath))
    assertEquals(6000, checksum(inputPath))

    
}

fun checksum(inputPath: String): Int {
    var (twice, thrice) = 0 to 0
    File(inputPath).useLines {
        it.forEach { s ->
            val letterCounter = HashMap<Char, Int>()
            s.forEach { c -> letterCounter[c] = (letterCounter[c] ?: 0) + 1 }

            val occurrencies = letterCounter.values
                .filter { count -> count in 2..3 }
                .toList()

            if (occurrencies.any { it == 2 })
                twice++
            if (occurrencies.any { it == 3 })
                thrice++
        }
    }
    return twice * thrice
}
