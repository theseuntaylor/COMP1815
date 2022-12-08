package coursework.algorithms;
class MergeSort {

    // End Condition for splitting array: Array size too small (1)
    fun mergeSort(mergeList: MutableList<String>): MutableList<String> {

        println("List before Merge sorting ==> $mergeList")

        if (mergeList.size <= 1) { //If the list size is <=1 stop splitting and return list
            return mergeList
        }

        val mid = mergeList.size / 2  //Split list
        val left = mergeList.subList(0, mid) //Define array slice: position 0 to middle
        val right = mergeList.subList(mid, mergeList.size) // Define array slice: mid to the end of the array


        return merge(mergeSort(left), mergeSort(right)) as MutableList<String> //Return the current sorted left and right lists
    }


    //Function for sorting then merging two halves.
    private fun merge(
        left: MutableList<String>, right: MutableList<String>
    ): MutableList<String> {
        var idxLeft = 0  // initialise index positions
        var idxRight = 0 // initialise index positions
        var ticks1 = 0
        var ticks2 = 0
        var ticks3 = 0
        var totalTicks = (ticks1 + ticks2 + ticks3)
        val newList: MutableList<String> = mutableListOf() //initialise mutable list

        while (idxLeft < left.count() && idxRight < right.count()) { //
             ticks1++  //Ticks1 increment
            if (left[idxLeft] <= right[idxRight]) { //
                newList.add(left[idxLeft])//
                idxLeft++ // index increment
            } else {
                newList.add(right[idxRight])
                idxRight++  //increment
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


        return(newList)

    }
}

// Algorithm Citation: https://chercher.tech/kotlin/bubble-sort-kotlin