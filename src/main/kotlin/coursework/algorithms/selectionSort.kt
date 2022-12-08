package coursework.algorithms;
class selectionSort {


    fun selectionSort(myArray: Array<Int>) {

        var minIdx: Int;
        for (i in 0 until myArray.size - 1) {
            minIdx = i;
            println("minimum to be $minIdx ${myArray[minIdx]}")
            for (j in (i + 1) until myArray.size) {
                if (myArray[j] < myArray[minIdx])
                    minIdx = j;
                if (minIdx != i) {
                    val tmp = myArray[minIdx]
                    myArray[minIdx] = myArray[i]
                    myArray[i] = tmp
                }

            }
            println("Minimum to be $minIdx")

        }

    }

    fun main() {
        val myArray = arrayOf(64, 25, 12, 22, 11)
        for (i in myArray) {
            println(i)

        }
        selectionSort(myArray)
        for (i in myArray) {
            println(i)

        }


    }

}