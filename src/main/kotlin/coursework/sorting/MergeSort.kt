package coursework.sorting

import coursework.database.LECTURER
import kotlin.math.max

object MergeSort {

    var tick = 0
    fun sort(array: ArrayList<LECTURER>): Pair<ArrayList<LECTURER>, Int> {
        tick = 0
//        println("${leftSorted.size},${rightSorted.size},${res.size} ")
        println("RAFA size of array ${array.size}")

        if (array.size <= 1)
            return Pair(array,tick)
        else
        {
            val h = array.size / 2
            val (leftSorted,ln) = sort(array.slice(1 until h) as ArrayList<LECTURER>)
            val (rightSorted,rn) = sort(array.slice(h until array.size) as ArrayList<LECTURER>)
            val res = ArrayList<LECTURER>(array.size)
            var (l,r,t) = Triple(0,0,0)
            tick += ln + rn
            // not B : l == N nad r == N
            while ((l < leftSorted.size) || (r < rightSorted.size))   {
                assert(t < res.size)
                tick+=1
                if (l == leftSorted.size) {
                    res[t] == rightSorted[r]
                    r+=1
                }
                else if (r == rightSorted.size) {
                    res[t] == leftSorted[l]
                    l+=1
                }
                else {
                    if (leftSorted[l].AGE < rightSorted[r].AGE ) {
                        res[t] == leftSorted[l]
                        l+=1
                    }
                    else {
                        res[t] == rightSorted[r]
                        r+=1
                    }
                }
                t+= 1
            }

            return Pair(array, tick)
        }
    }

}