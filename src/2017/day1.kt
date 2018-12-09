package `2017`

import java.lang.NumberFormatException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

fun main() {
    println("Advent of code 2017")
    println("1st December - Captcha")

    assertEquals(3, captcha("1122"))
    assertEquals(0, captcha("1234"))
    assertEquals(4, captcha("1111"))
    assertEquals(9, captcha("912121219"))
    assertEquals(0, captcha(""))
    assertFailsWith(NumberFormatException::class) {
        captcha("42332fsa")
    }

    println("Success")

}

fun captcha(input: String) : Int {
    var sum = 0
    for (i in 0 until input.length) {

        if(input[i] !in '0'..'9')
            throw NumberFormatException()
        val compare = if (i == input.length - 1) {
            input[0]
        } else {
            input[i+1]
        }

        if(input[i] == compare) {
            sum += input[i].toString().toInt()
        }
    }

    return sum
}
