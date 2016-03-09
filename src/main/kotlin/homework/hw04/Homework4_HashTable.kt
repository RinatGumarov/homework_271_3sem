package homework.hw04

import java.util.*

/** Implementation of hash table. */
public class HashTable(val size : Int) : Set() {

    private var table = Array(this.size, { LinkedList<Int>() })

    /** Inserts element in hash table. */
    public override fun insert(elem : Int) : HashTable {
        val hashCode = elem.hashCode() % this.size
        table[hashCode].add(elem)
        return this
    }

    /** Removes element from hash table. */
    public override fun remove(elem : Int) : HashTable {
        val hashCode  = elem.hashCode() % this.size
        val cellList = table[hashCode]
        if (cellList.contains(elem)) cellList.remove(elem)
        return this
    }

    /** Returns true if hash table contains given element. */
    public override fun contains(elem : Int) : Boolean {
        val hashCode = elem.hashCode() % this.size
        return table[hashCode].contains(elem)
    }

    /** Converts hash table to list. */
    public fun toList() : List<Int> {
        var list = LinkedList<Int>()
        for (i in table) {
            list.addAll(i)
        }
        return list
    }

    /** Returns hash table which is union of given hash tables. */
    public override fun unite(set : Set) : HashTable {
        var result = HashTable(size)
        for (i in this.toList()) result = result.insert(i)
        for (i in (set as HashTable).toList())
            if (!this.contains(i)) result = result.insert(i)
        return result
    }

    /** Returns hash table which is intersection of given hash tables. */
    public override fun intersect(set : Set) : HashTable {
        var result = HashTable(size)
        for (i in (set as HashTable).toList())
            if (this.contains(i)) result = result.insert(i)
        return result
    }

    /** Prints hash table. */
    public fun toText() {
        for (i in table) println(i)
    }
}