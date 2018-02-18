package com.temas.tasks.dzhindash

import junit.framework.TestCase.assertEquals
import org.junit.Test


class Tests {
    @Test
    fun testPermutations() {
        val list = arrayOf(1,2,3)
        val permutations = Permutations(list).all()
        assertEquals(6, permutations.size)
        assertEquals(listOf(1,2,3), permutations[0])
        assertEquals(listOf(1,3,2), permutations[1])
        assertEquals(listOf(2,1,3), permutations[2])
        assertEquals(listOf(2,3,1), permutations[3])
        assertEquals(listOf(3,1,2), permutations[4])
        assertEquals(listOf(3,2,1), permutations[5])
    }

    @Test
    fun testPrintSolution() {
        val s = Solution(Names.values(), arrayOf(1,2,3,4,5),
                Color.values(), City.values(), Drink.values(), Jew.values())
        println(s)
    }

}