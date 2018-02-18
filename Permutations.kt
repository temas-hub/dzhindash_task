package com.temas.tasks.dzhindash

import java.util.ArrayList

/**
 *
 */
class Permutations<T>(private val allPossibleItems: Array<T>) {
    fun all() : ArrayList<List<T>>{
        val result = ArrayList<List<T>>()
        fun generatePerm(item: T?, tmpLst: MutableList<T>) {
            if (item != null) {
                tmpLst.add(item)
            }
            if (tmpLst.size == allPossibleItems.size) {
                result.add(tmpLst)
                return
            }
            allPossibleItems
                    .filter { !tmpLst.contains(it) }
                    .forEach { generatePerm(it, ArrayList(tmpLst)) }
        }
        generatePerm(null, ArrayList())
        return result
    }


}