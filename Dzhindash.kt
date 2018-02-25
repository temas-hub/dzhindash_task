package com.temas.tasks.dzhindash

import kotlin.collections.ArrayList


class Solution (
        val names: Array<Names>,
        val pos: Array<Int>,
        val col: Array<Color>,
        val city: Array<City>,
        val drink: Array<Drink>,
        val jew: Array<Jew>) {
    fun indOf(name: Names) = names.indexOf(name)
    fun indOf(p: Int) = pos.indexOf(p)
    fun indOf(c: Color) = col.indexOf(c)
    fun indOf(ci: City) = city.indexOf(ci)
    fun indOf(dr: Drink) = drink.indexOf(dr)
    fun indOf(j: Jew) = jew.indexOf(j)

    fun isNear(indBase: Int, indTo: Int) =
            pos[indBase] == pos[indTo] - 1 || pos[indBase] == pos[indTo] + 1

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("/-\n")
        sb.append(names.toTableString())
        sb.append(pos.toTableString())
        sb.append(col.toTableString())
        sb.append(city.toTableString())
        sb.append(drink.toTableString())
        sb.append(jew.toTableString())
        sb.append("\\-\n")
        return sb.toString()
    }
}

fun <T>Array<T>.toTableString() = joinToString(separator = "\t",prefix = "",postfix = "") + "\n"


enum class Names {
    Conti, Markolla, Finch, Winslow, Naciu
}

enum class Color {
    W,G,P,R,B
}

enum class City {
    F,S,B,D,M
}

enum class Jew {
    Persten, Portsigar, Brilliant, Kulon, Orden
}

enum class Drink {
    Cyder, Absynth, Cocktail, Whiskey, Rum
}

typealias Rule = (Solution) -> Boolean


val ruleList = listOf<Rule>(
        { s -> s.indOf(Names.Markolla) == s.indOf(Color.P) },
        { s -> s.indOf(Color.W) == s.indOf(2)},
        { s -> s.pos[s.indOf(Color.B)] == s.pos[s.indOf(Color.R)] + 1},
        { s -> s.indOf(Color.B) == s.indOf(Drink.Absynth)},
        { s -> s.indOf(City.F) == s.indOf(Color.G)},
        { s -> s.isNear(s.indOf(Jew.Persten), s.indOf(City.F))},
        { s -> s.indOf(Names.Naciu) == s.indOf(Jew.Orden)},
        { s -> s.indOf(City.S) == s.indOf(Jew.Brilliant)},
        { s -> s.isNear(s.indOf(Jew.Kulon), s.indOf(City.M))},
        { s -> s.isNear(s.indOf(City.M), s.indOf(Drink.Whiskey))},
        { s -> s.indOf(Names.Finch) == s.indOf(Drink.Cyder)},
        { s -> s.indOf(City.B) == s.indOf(Drink.Rum)},
        { s -> s.indOf(City.B) != s.indOf(3)},
        { s -> s.indOf(3) == s.indOf(Drink.Cocktail)},
        { s -> s.indOf(Names.Winslow) == s.indOf(City.D)}
)


fun main(args: Array<String>) {
    val names = Names.values()
    val pos = arrayOf(2,3,4,5)
    val allPos = Permutations(pos).all()
    val allCol = Permutations(Color.values()).all()
    val allCity = Permutations(City.values()).all()
    val allDrink = Permutations(Drink.values()).all()
    val allJew = Permutations(Jew.values()).all()

    var correctCnt = 0
    var varCnt = 0
    val chunk = 10_000_000

    val correctSolutions = ArrayList<Solution>()

    allPos.forEach {
        pos -> allCol.forEach {
            col -> allCity.forEach {
                cities -> allDrink.forEach {
                    drinks -> allJew.forEach {

        val newPos = Array(5, { idx -> if (idx > 0) pos[idx-1] else 1 })

        val solution = Solution(names,
                newPos,
                col.toTypedArray(),
                cities.toTypedArray(),
                drinks.toTypedArray(),
                it.toTypedArray()
        )
        varCnt++
        if (checkRules(solution)) {
            correctSolutions.add(solution)
            correctCnt++
        }
        if (varCnt % chunk == 0) {
            println("all: ${varCnt / 1_000_000}M, found: $correctCnt")
        }
    }}}}}
    println(correctSolutions.joinToString("\n"))
}

fun checkRules(solution: Solution) = ruleList.all { it.invoke(solution) }


