import java.io.File
import java.util.*
import kotlin.collections.HashMap
import kotlin.test.assertEquals


fun main() {
    val inputPath = "res/exInput.txt" //"res/4-2018-input.txt"
    val timeplan = getTimeplan(parse(inputPath))

    assertEquals(99759, mostAsleep(timeplan))


//    println(part1(inputPath))

}

private fun parse(inputPath: String) : List<String> {
    return File(inputPath).readLines().sorted()
}

fun mostAsleep(timeplan: Map<Int, MutableList<Int>> ): Int {
    val guidesAsleep = TreeMap<Int, Int>()

    timeplan.forEach {
        val asleepIntervals = LinkedList<Pair<Int, Int>>()
        val asleepMinutes = IntArray(60)

        for (i in 0 until it.value.size step 2) {
            val (start, end) = it.value[i] to it.value[i+1]
            asleepIntervals.add(Pair(start, end))
            for (m in start until end)
                asleepMinutes[m]++
        }

        val totAsleep = asleepIntervals.fold(0) { acc, pair ->
            acc + (pair.second - pair.first)
        }

        guidesAsleep[totAsleep] = it.key * (asleepMinutes.indices.maxBy { asleepMinutes[it] } ?: -1)
    }

    return guidesAsleep.lastEntry().value
}

private fun getTimeplan(lines: List<String>) : Map<Int, MutableList<Int>> {
    val regexMinutes = """\[.+:(\d\d)]""".toRegex()
    val regexGuard = """#(\d+)""".toRegex()
    val timeplan = HashMap<Int, MutableList<Int>>()
    var guardId = -1

    lines.forEach {
        when {
            //TODO check errors
            it.contains("Guard") -> {
                guardId = regexGuard.find(it)!!.destructured.component1().toInt()
                timeplan.putIfAbsent(guardId, LinkedList())
            }
            else -> {
                val minute = regexMinutes.find(it)!!.destructured.component1().toInt()
                timeplan[guardId]?.add(minute)
            }
        }
    }

    return timeplan

}

