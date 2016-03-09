package homework.hw01

fun heapSort(array : Array<Int>) {
    // builds the heap in array
    for (i in array.size / 2 downTo 0) {
        shiftDown(array, i, array.size)
    }


    // sorts array in ascending order
    for (i in array.size - 1 downTo 1) {
        var temp = array[0]
        array[0] = array[i]
        array[i] = temp
        shiftDown(array, 0, i)
    }
}

/** Repair the heap whose root element is at index 'start'. */
fun shiftDown(array : Array<Int>, start : Int, end : Int) {
    var done = false
    var root = start

    while ((root * 2 + 1 < end) && (!done)) {
        var child = root * 2 + 1

        if ((child + 1 != end) && (array[child] <= array[child + 1]))
            child++

        if (array[root] < array[child]) {
            var temp = array[root]
            array[root] = array[child]
            array[child] = temp
            root = child
        }
        else
            done = true
    }
}

/** The 2nd task. */
open class Tree {}

class Empty : Tree() {}
class Leaf(val value : Int) : Tree() {}
class Node(val value : Int, val left : Tree, val right : Tree) : Tree() {}

/** Finds way in tree from root to leafs with maximum sum of nodes. */
fun maxWay(f : (Int, Int) -> Int, acc : Int, tree : Tree) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf  -> return f(acc, tree.value)
        is Node  -> {
            val temp = f(acc, tree.value)
            return Math.max(maxWay(f, temp, tree.left), maxWay(f, temp, tree.right))
        }
        else -> throw Exception("Error")
    }
}

/** The 3rd task. */
fun fold(f : (Int, Int) -> Int, g : (Int, Int) -> Int, acc : Int, tree : Tree) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf  -> return f(acc, tree.value)
        is Node  -> {
            val temp = f(acc, tree.value)
            return f(temp, g(fold(f, g, acc, tree.left), fold(f, g, acc, tree.right)))
        }
        else -> throw Exception("Error")
    }
}

/** The 4th task. */
open class Peano {}

class Zero : Peano() {}
class S(val value : Peano) : Peano() {}

/** Generates a peano number. */
fun peanoGen(n : Int) : Peano {
    when (n) {
        0    -> return Zero()
        else -> return S(peanoGen(n - 1))
    }
}

/** Converts a peano number to integer. */
fun peanoToInt(p : Peano) : Int {
    when (p) {
        is Zero -> return 0
        is S    -> return 1 + peanoToInt(p.value)
        else    -> throw Exception("Error")
    }
}

/** Sum for peano numbers. */
fun peanoSum(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return b
        is S    -> {
            when (b) {
                is Zero -> return a
                is S    -> return peanoSum(a.value, S(b))
                else    -> throw Exception ("Error")
            }
        }
        else -> throw Exception ("Error")
    }
}

/** Subtraction for peano numbers. */
fun peanoSub(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return b
        is S    -> {
            when (b) {
                is Zero -> return a
                is S    -> return peanoSub(a.value, b.value)
                else    -> throw Exception ("Error")
            }
        }
        else -> throw Exception ("Error")
    }
}

/** Multiplication for peano numbers. */
fun peanoMult(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return a
        is S    -> return peanoSum(b, peanoMult(a.value, b))
        else    -> throw Exception ("Error")
    }
}

/** Exponentiation for peano number. */
fun peanoExp(x : Peano, y : Peano) : Peano {
    when (y) {
        is Zero -> return S(y)
        is S    -> return peanoMult(x, peanoExp(x, y.value))
        else    -> throw Exception ("Error")
    }
}