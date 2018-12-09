package `2018`

import java.io.File
import kotlin.test.assertEquals

fun main() {
    val inputPath = "resources/2018/day2.txt"
//    val exInputPath = "resources/example.txt"

//    assertEquals("fgij", `2018`.findCommonLetters(exInputPath))
    assertEquals("pbykrmjmizwhxlqnasfgtycdv", findCommonLetters(inputPath))
//    assertEquals("ab", `2018`.commonLettersIfOnly1Diff("abc", "abd"))
    assertEquals(6000, checksum(inputPath))


}

fun findCommonLetters(inputPath: String): String {
    val lines = File(inputPath).readLines()
    for(i in 0 until lines.size-1) {
        for(j in i+1 until lines.size)
            return commonLettersIfOnly1Diff(lines[i], lines[j]) ?: continue
    }

    throw Exception("no ID found")
}

fun commonLettersIfOnly1Diff(s1: String, s2: String) : String? {
    if(s1.length != s2.length)
        return null
    //assuming strings have same case as in the input file
    var diff = 0
    val res = StringBuilder()

    for (i in 0 until s1.length) {
        if (s1[i] != s2[i])
            diff++
        else res.append(s1[i])
    }

    return if (diff==1) res.toString() else null

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
