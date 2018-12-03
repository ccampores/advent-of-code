import java.io.File
import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertEquals

data class Rect(val dx:Pair<Int, Int>, val dy:Pair<Int, Int>)
data class Point(val x:Int, val y:Int)

fun findOverlapping(inputPath: String): Int {

    val claims = parse(inputPath)
    val overlapPoints = HashSet<Point>()
    for(i in 0 until claims.size-1) {
        for(j in i+1 until claims.size) {
            val overlap = overlappingArea(claims[i], claims[j])
            if (overlap != null)
                overlapPoints.addAll(getListOfPoints(overlap.dx, overlap.dy))
        }
    }

    return overlapPoints.size
}

fun parse(inputPath: String): List<Rect> {
    val regex = """#\d+ @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()
    val claims = ArrayList<Rect>()

    File(inputPath).useLines { sequence ->
        sequence.forEach {
            //TODO error checks
            val (left, top, w, h) = regex.find(it)!!.destructured
            val dx = Pair(left.toInt()+1, left.toInt()+w.toInt())
            val dy = Pair(top.toInt()+1, top.toInt()+h.toInt())
            claims.add(Rect(dx, dy))
        }
    }

    return claims
}

fun getListOfPoints(dx: Pair<Int, Int>, dy:Pair<Int, Int>) : List<Point> {
    val pointsList = ArrayList<Point>()
    for(i in dx.first..dx.second) {
        for(j in dy.first..dy.second)
            pointsList.add(Point(i, j))
    }
    return pointsList
}

fun isOverlapInterval(i1:Pair<Int, Int>, i2:Pair<Int, Int>) : Boolean {
    return when {
        i1.first <= i2.first -> i1.second >= i2.first
        else -> i2.second >= i1.first
    }
}

fun isOverlapArea(s1:Rect, s2:Rect) : Boolean {
    return isOverlapInterval(s1.dx, s2.dx) && isOverlapInterval(s1.dy, s2.dy)
}

fun overlappingArea(s1:Rect, s2:Rect) : Rect? {
    return when {
        isOverlapArea(s1, s2) -> {
            val overlapDx = max(s1.dx.first, s2.dx.first) to min(s1.dx.second, s2.dx.second)
            val overlapDy = max(s1.dy.first, s2.dy.first) to min(s1.dy.second, s2.dy.second)
            Rect(overlapDx, overlapDy)
        }
        else -> null
    }
}

fun main() {
    val inputPath = "res/3-2018-input.txt"

    assertEquals(107663, findOverlapping(inputPath))
}

