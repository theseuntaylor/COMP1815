package coursework.algorithms;

import coursework.database.BOOKS
import java.util.*

class MergeSort {

    // End Condition for splitting array: Array size too small (1)
    fun mergeSort(mergeList: MutableList<BOOKS>, sortType: Int): Pair<MutableList<BOOKS>, Int> {

        println("List before Merge sorting ==> $mergeList")

        if (mergeList.size <= 1) { //If the list size is <=1 stop splitting and return list
            return Pair(mergeList, mergeList.count())
        }

        val mid = mergeList.size / 2  //Split list
        val left = mergeList.subList(0, mid) //Define array slice: position 0 to middle
        val right = mergeList.subList(mid, mergeList.size) // Define array slice: mid to the end of the array

        val list = merge(mergeSort(left, sortType).first, mergeSort(right, sortType).first, sortType)

        println("map ====> $list")
        println("map ====> ${list.first}")
        println("map ====> ${list.second}")

        return Pair(
            list.first,
            list.second
        ) // merge(mergeSort(left), mergeSort(right)) as MutableList<String> //Return the current sorted left and right lists
    }


    //Function for sorting then merging two halves.
    private fun merge(
        left: MutableList<BOOKS>, right: MutableList<BOOKS>, sortType: Int
    ): Pair<MutableList<BOOKS>, Int> {
        var idxLeft = 0  // initialise index positions
        var idxRight = 0 // initialise index positions
        var ticks1 = 0
        var ticks2 = 0
        var ticks3 = 0
        val newList: MutableList<BOOKS> = mutableListOf() //initialise mutable list

        while (idxLeft < left.count() && idxRight < right.count()) { //
            ticks1++  //Ticks1 increment
            if (sortType == 1) {
                if (left[idxLeft].BOOK_NAME.lowercase(Locale.getDefault()) <= right[idxRight].BOOK_NAME.lowercase(Locale.getDefault())) { //
                    newList.add(left[idxLeft])//
                    idxLeft++ // index increment
                } else {
                    newList.add(right[idxRight])
                    idxRight++  //increment
                }
            } else {
                if (left[idxLeft].YEAR.toString().lowercase(Locale.getDefault()) <= right[idxRight].YEAR.toString()
                        .lowercase(Locale.getDefault())
                ) { //
                    newList.add(left[idxLeft])//
                    idxLeft++ // index increment
                } else {
                    newList.add(right[idxRight])
                    idxRight++  //increment
                }
            }
        }

        while (idxLeft < left.size) {
            ticks2++ //Ticks2 Increment
            newList.add(left[idxLeft])
            idxLeft++
        }

        while (idxRight < right.size) {
            ticks3++//Ticks 3 Increment
            newList.add(right[idxRight])
            idxRight++
        }
        println("List after merge sorting ==> $newList")

        val totalTicks = (ticks1 + ticks2 + ticks3)

        return Pair(newList, totalTicks)

    }
}

// Algorithm Citation: https://chercher.tech/kotlin/bubble-sort-kotlin