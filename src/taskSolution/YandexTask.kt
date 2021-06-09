package com.learnxinyminutes.kotlin.taskSolution

class YandexTask {
    fun getSolutionTaskOne(inputX: Array<Int>, inputY: Array<Int>): List<Int> {
        val result = mutableListOf<Int>()
        val sX = inputX.toSet()
        val sY = inputY.toSet()

        for (x in sX)
            for (y in sY) {
                if (x == y) {
                    result.add(x)
                }
            }

        return result
    }
}