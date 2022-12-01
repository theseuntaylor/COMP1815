package coursework.sorting
class MergeSort {

    // End Condition for splitting array: Array size too small (1)
    fun mergeSort(list: MutableList<String>): MutableList<String> {

        println("List before sorting ==> $list")

        if (list.size <= 1) { //If the list size is <=1 stop splitting and return list
            return list
        }

        val mid = list.size / 2  //Split list
        val left = list.subList(0, mid) //Define array slice: position 0 to middle
        val right = list.subList(mid, list.size) // Define array slice: mid to the end of the array


        return merge(mergeSort(left), mergeSort(right)) as MutableList<String> //Return the current sorted left and right lists
    }


    //Function for sorting then merging two halves.
    private fun merge(
        left: MutableList<String>, right: MutableList<String>
    ): List<String> {
        var idxLeft = 0  // initialise index positions
        var idxRight = 0 // initialise index positions
        val newList: MutableList<String> = mutableListOf() //initialise mutable list

        while (idxLeft < left.count() && idxRight < right.count()) { //
            if (left[idxLeft] <= right[idxRight]) { //
                newList.add(left[idxLeft])//
                idxLeft++ // increment
            } else {
                newList.add(right[idxRight])
                idxRight++  //increment
            }
        }

        while (idxLeft < left.size) {
            newList.add(left[idxLeft])
            idxLeft++
        }

        while (idxRight < right.size) {
            newList.add(right[idxRight])
            idxRight++
        }
        println("List merge after sorting ==> $newList")
        println(newList.size)

        return newList
    }
}