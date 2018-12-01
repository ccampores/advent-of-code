import java.io.File
import kotlin.test.assertEquals

fun main() {
    assertEquals(516, sumFrequencies("res/1-2018-input.txt"))
}

fun sumFrequencies(inputPath: String) : Int{
    //TODO check input
    return File(inputPath).useLines {
        it.fold(0) { sum, el -> sum + el.toInt() }
    }
}



