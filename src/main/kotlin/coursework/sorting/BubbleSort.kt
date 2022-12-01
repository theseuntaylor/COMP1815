package coursework.sorting

class BubbleSort {

    fun sortByBubbleSort(bubbleArr: MutableList<String>): MutableList<String> {

        println("List before sorting ==> $bubbleArr")

        var sTrigger = 1
        while (sTrigger == 1) {   // While True: Run
            sTrigger = 0
            for (i in 0 until bubbleArr.size - 1) { //Array Positioning
                if (bubbleArr[i] > bubbleArr[i + 1]) {  //Comparison
                    val tmp = bubbleArr[i]
                    bubbleArr[i] = bubbleArr[i + 1]
                    bubbleArr[i + 1] = tmp

                    sTrigger = 1  // for next iteration
                }
            }
        }
        println("List bubble after sorting ==> $bubbleArr")

        return bubbleArr
    }

}