package coursework.algorithms

import coursework.database.BOOKS
import java.util.*
import kotlin.collections.ArrayList

class BubbleSort {

    fun sortByBubbleSort(bubbleList: ArrayList<BOOKS>, sortType: Int): Pair<List<BOOKS>, Int> {

        println("List before sorting ==> $bubbleList")

        var swapTrigger = 1
        var tick = 0
        while (swapTrigger == 1) {
            swapTrigger = 0
            for (i in 0 until bubbleList.size - 1) {
                tick++
                if (sortType == 1) {
                    if (bubbleList[i].BOOK_NAME.lowercase() > bubbleList[i + 1].BOOK_NAME.lowercase(Locale.getDefault())) {  //Comparison
                        val tmp = bubbleList[i]
                        bubbleList[i] = bubbleList[i + 1]
                        bubbleList[i + 1] = tmp
                        swapTrigger = 1  // for next iteration
                    }
                } else {
                    if (bubbleList[i].YEAR.toString() > bubbleList[i + 1].YEAR.toString()) {  //Comparison
                        val tmp = bubbleList[i]
                        bubbleList[i] = bubbleList[i + 1]
                        bubbleList[i + 1] = tmp
                        swapTrigger = 1
                    }
                }

            }
        }
        println("List bubble after sorting ==> $bubbleList")

        return Pair(bubbleList, tick)
    }

}